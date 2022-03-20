package com.example.pizzariadonmortandello;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorJoiner;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pizza> pizzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            this.pizzas = new ArrayList<>();
        }else{
            this.pizzas = savedInstanceState.getParcelableArrayList("pizza");
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("pizza", this.pizzas);
    }

    public void onClickCadastrar(View view){
        Intent intent = new Intent(this, CadastroPizzaActivity.class);
        startActivityForResult(intent,500);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode==500 && data!= null){

            String nome = data.getStringExtra("nome");
            Double preco = data.getDoubleExtra("preco", 0);
            String descricao = data.getStringExtra("descricao");
            Pizza pizza = new Pizza(nome, preco, descricao);

            this.pizzas.add(pizza);
        }
    }

    public void onClickCardapio(View view){
        Intent sendIntent = new Intent(this, CardapioActivity.class);
        sendIntent.putExtra(CardapioActivity.PIZZAS_KEY,this.pizzas);
        startActivity(sendIntent);
    }
}
