package com.example.pizzariadonmortandello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroPizzaActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtPreco;
    private TextView txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pizza);
        txtNome = findViewById(R.id.txtNome);
        txtPreco = findViewById(R.id.txtPreco);
        txtDescricao = findViewById(R.id.txtDescricao);
    }

    public void onClickSalvar(View view){
        if(verificarCampos()) {
            Intent data = new Intent();
            data.putExtra("nome", txtNome.getText().toString());
            data.putExtra("preco", Double.parseDouble(txtPreco.getText().toString()));
            data.putExtra("descricao", txtDescricao.getText().toString());
            setResult(RESULT_OK, data);
            Toast.makeText(this, "Pizza adicionada com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    private boolean verificarCampos() {
        if((!txtNome.getText().toString().isEmpty()) || (!txtPreco.getText().toString().isEmpty()) || (!txtDescricao.getText().toString().isEmpty()))
            return true;
        else
            return false;
    }

    public void onClickPreco(View view){
        txtPreco.setText("");
    }
}
