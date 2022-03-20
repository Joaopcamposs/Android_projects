package iftm.edu.br.fiscaldebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tela_login extends AppCompatActivity {

    private EditText etxt_nome;
    private EditText etxt_email;
    private Button btn_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        this.etxt_email = findViewById(R.id.etxt_email);
        this.etxt_nome = findViewById(R.id.etxt_nome);
        this.btn_entrar = findViewById(R.id.btn_entrar);
    }

    public void OnClickEntrar(View view)
    {
        if(!etxt_nome.getText().toString().isEmpty() || !etxt_email.getText().toString().isEmpty()){
            Intent intent = new Intent(this,Tela_relatar.class);
            startActivity(intent);
            //MainActivity obj = new MainActivity();
            //obj.teste_login.Set(true); //terminar de setar TODO
        }
        else{
            //mostar mensagem erro;
        }
    }
}
