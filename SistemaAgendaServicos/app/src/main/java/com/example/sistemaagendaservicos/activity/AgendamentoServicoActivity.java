package com.example.sistemaagendaservicos.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemaagendaservicos.R;
import com.example.sistemaagendaservicos.modelo.Agendamento;
import com.example.sistemaagendaservicos.util.DialogProgress;
import com.example.sistemaagendaservicos.util.Util;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AgendamentoServicoActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private EditText editText_Nome;
    private TextView textView_numeroContato;
    private CheckBox checkBox_WhatsApp;
    private TextView textView_Email;
    private CheckBox checkBox_Barba;
    private CheckBox checkBox_Cabelo;
    private CardView cardView_Agendar;

    private ArrayList<String> data = new ArrayList<String>();

    private GoogleApiClient googleApiClient_Numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_servico);

        data = getIntent().getStringArrayListExtra("data");

        editText_Nome = findViewById(R.id.editText_AgendamentoServico_Nome);
        textView_numeroContato = findViewById(R.id.editText_AgendamentoServico_Numero);
        checkBox_WhatsApp = findViewById(R.id.checkbox_AgendamentoServico_WhatsApp);
        textView_Email = findViewById(R.id.editText_AgendamentoServico_Email);
        checkBox_Barba = findViewById(R.id.checkbox_AgendamentoServico_Barba);
        checkBox_Cabelo = findViewById(R.id.checkbox_AgendamentoServico_Cabelo);
        cardView_Agendar = (CardView)findViewById(R.id.cardView_AgendamentoServico_Agendar);

        cardView_Agendar.setOnClickListener(this);

        googleApiClient_Numero = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .enableAutoManage(this, this).addApi(Auth.CREDENTIALS_API).build();

        obterNumeroContato(); //todo nao capturou telefone no emulado (JP)

        obterEmail(); //todo nao capturou email no emulador, testei outro metodo e nao foi tambem (JP)


    }
    //------------------------------------------ ACAO DE CLICK ----------------------------------------------------
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardView_AgendamentoServico_Agendar:
                agendar();
                break;
        }


    }

    //------------------------------------------ OBTER NUMERO TELEFONE ----------------------------------------------------
    private void obterNumeroContato(){

        HintRequest hintRequest = new HintRequest.Builder().setHintPickerConfig(new CredentialPickerConfig.Builder()
                .setShowCancelButton(false).build()).setPhoneNumberIdentifierSupported(true).build();

        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient_Numero,hintRequest);

        try {
            startIntentSenderForResult(intent.getIntentSender(), 122, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------ OBTER EMAIL ----------------------------------------------------
    private void obterEmail(){

        AccountManager accountManager = AccountManager.get(this);

        Account[] accounts = accountManager.getAccounts();

        for(Account account: accounts){
            String email = account.name;

            if(email.contains("@")){
                textView_Email.setText(email);
                break;
            }
        }
    }


    //------------------------------------------ METODOS NUMERO TELEFONE ----------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 122){

            if(resultCode == RESULT_OK){

            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);

            if (!credential.getId().isEmpty()){
                textView_numeroContato.setText(credential.getId());
            }else{
                Toast.makeText(getBaseContext(), "Escolha um numero de contato para continuar", Toast.LENGTH_LONG).show();
            }

            }else{
                dialogoNumeroContato();
            }
        }
    }

    private void dialogoNumeroContato(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Escolha obrigatória")
                .setCancelable(false)
                .setMessage("Escolha um número de telefone para agendar um horário.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        obterNumeroContato();
                    }
                })
                .setNegativeButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.show();

    }

    //------------------------------------------ AGENDAR ----------------------------------------------------
    private void agendar(){

        String nome = editText_Nome.getText().toString();
        String contato = textView_numeroContato.getText().toString();
        boolean whatsapp = checkBox_WhatsApp.isChecked();
        String email = textView_Email.getText().toString();
        boolean barba = checkBox_Barba.isChecked();
        boolean cabelo = checkBox_Cabelo.isChecked();

        if(!nome.isEmpty()){
            if(!cabelo && !barba){
                Toast.makeText(getBaseContext(), "Escolha qual serviço gostaria de agendar!", Toast.LENGTH_SHORT).show();
            }else{
                if(Util.statusInternet_MoWi(getBaseContext())){
                    //Toast.makeText(getBaseContext(), " "+nome+" "+contato+" "+email+" ", Toast.LENGTH_SHORT).show();
                    agendarFirebase(nome, contato, whatsapp, email, barba, cabelo);
                }else{
                    Toast.makeText(getBaseContext(), "Erro. Verifique sua conexão com a internet.", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(getBaseContext(), "Insira seu nome para agendar!", Toast.LENGTH_SHORT).show();
        }

    }

    public void agendarFirebase(String nome, String contato, boolean whatsapp, String email, boolean barba, boolean cabelo){

        Agendamento agendamento = new Agendamento(nome, contato, whatsapp, email, barba, cabelo);

        final DialogProgress dialogProgress = new DialogProgress();

        dialogProgress.show(getSupportFragmentManager(), "dialog");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference reference = firebaseDatabase.getReference().child("BD").child("Calendario").child("HorariosAgendados")
                .child(data.get(2)).child("Mes").child(data.get(1)).child("dia").child(data.get(0));

        reference.child(data.get(3)).setValue(agendamento).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    dialogProgress.dismiss();
                    Toast.makeText(getBaseContext(), "Agendamento feito com sucesso!", Toast.LENGTH_SHORT).show();
                }else{

                    dialogProgress.dismiss();
                    Toast.makeText(getBaseContext(), "Falha ao agendar, verifique os dados e tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //------------------------------------------ METODOS GOOGLE ----------------------------------------------------

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}