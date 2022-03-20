package br.edu.iftm.pdm.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickSend(View view){
        Intent sendIntent = new Intent(this, MessagesActivity.class);
        startActivity(sendIntent);
    }
}
