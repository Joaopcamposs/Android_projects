package com.example.sistemaagendaservicos.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sistemaagendaservicos.R;
import com.example.sistemaagendaservicos.adapter.AdapterListView;
import com.example.sistemaagendaservicos.util.Util;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HorariosActivity extends AppCompatActivity implements AdapterListView.ClickItemListView {
    private ListView listView;
    private AdapterListView adapterListView;
    private List<String> horarios = new ArrayList<String>();
    private List<String> horarios_Temp = new ArrayList<String>();
    private ArrayList<String> data = new ArrayList<String>();

    //Firebase
    private FirebaseDatabase database;
    private DatabaseReference referenceBuscarHorario;
    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        listView =  (ListView) findViewById(R.id.listView);

        database = FirebaseDatabase.getInstance();
        data = getIntent().getStringArrayListExtra("data");

        configurarListView();

        carregarHorarioFuncionamento();


    }

    private void configurarListView(){
        adapterListView = new AdapterListView(this, this.horarios, this);

        listView.setAdapter(adapterListView);
    }

    private void carregarHorarioFuncionamento(){
        DatabaseReference reference = database.getReference().child("BD").child("Calendario").child("HorariosFuncionamento");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        String horario = snapshot.getValue(String.class);

                        horarios.add(horario);
                        horarios_Temp.add(horario);
                    }
                    adapterListView.notifyDataSetChanged();
                    buscarHorariosReservados();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void buscarHorariosReservados(){
        referenceBuscarHorario = database.getReference().child("BD").child("Calendario").child("HorariosAgendados").child(data.get(2)).child("Mes").child(data.get(1)).child("dia").child(data.get(0));
        Log.d(referenceBuscarHorario.getKey(), "buscarHorariosReservados: ");
        if (childEventListener == null){

            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    // pasta 08:00 hrs
                    String chave = dataSnapshot.getKey();  // le o nome da pasta

                    int index = horarios.indexOf(chave);

                    String horario  = chave + " - Reservado";

                    horarios.set(index, horario);

                    adapterListView.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    String chave = dataSnapshot.getKey();  // le o nome da pasta

                    String horario  = chave + " - Reservado";

                    int index = horarios.indexOf(horario);

                    horarios.set(index, chave);

                    adapterListView.notifyDataSetChanged();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            };
            referenceBuscarHorario.addChildEventListener(childEventListener);
        }

    }

    @Override
    public void clickItem(String horario, int posicao) {
        if (Util.statusInternet_MoWi(getBaseContext())){
            consultarHorarioSelecionadoBanco(horario, posicao);
        }else{
            Toast.makeText(getBaseContext(), "Erro sem conexão com a internet", Toast.LENGTH_LONG).show();
        }
    }

    private void consultarHorarioSelecionado(String horario, int posicao){
        if (horario.contains("Reservado")){
            Toast.makeText(getBaseContext(), "Já existe um agendamento para esse horário", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext(), "Agendamento disponível", Toast.LENGTH_LONG).show();
            //Chamar nossa proxima activity
        }
    }

    private void consultarHorarioSelecionadoBanco(final String horario, int posicao){
        DatabaseReference reference = database.getReference().child("BD").
                child("Calendario").child("HorariosAgendados").child(data.get(2)).
                child("Mes").child(data.get(1)).child("dia").child(data.get(0)).child(horarios_Temp.get(posicao));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(getBaseContext(), "Já existe um agendamento para esse horário", Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(getBaseContext(), "Agendamento disponível", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getBaseContext(),AgendamentoServicoActivity.class);

                    //dataList.add(dia); //posicao 0
                    //dataList.add(mes); //posicao 1
                    //dataList.add(ano); //posicao 2
                    //data.add(horario); //posicao 3

                    data.add(3, horario);

                    intent.putExtra("data", data);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (childEventListener == null){
            referenceBuscarHorario.removeEventListener(childEventListener);
            childEventListener = null;

        }
    }
}