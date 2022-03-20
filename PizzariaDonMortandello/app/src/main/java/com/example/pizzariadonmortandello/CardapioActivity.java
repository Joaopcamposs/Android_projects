package com.example.pizzariadonmortandello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CardapioActivity extends AppCompatActivity {
    public static final String PIZZAS_KEY = "CardapioActivity.PIZZAS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        ArrayList<Pizza> pizzas  = getIntent().getParcelableArrayListExtra(PIZZAS_KEY);
        TextView txtCardapio =  findViewById(R.id.txtCardapio);

        if(pizzas != null) {
            for (Pizza pizza : pizzas
            ) {
                txtCardapio.append(pizza.toString());
            }
        }
    }
}
