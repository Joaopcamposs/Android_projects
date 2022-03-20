package iftm.edu.br.fiscaldebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_relatar;
    private Button btn_visu;
    private Button btn_estati;
    private Boolean teste_login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btn_estati = findViewById(R.id.btn_estati);
        this.btn_relatar = findViewById(R.id.btn_relatar);
        this.btn_visu = findViewById(R.id.btn_visu);
    }

    public void OnClickRelatar(View view){
        if(teste_login == false)
        {
            Intent intent = new Intent(this,Tela_login.class);
            startActivity(intent);
        }
        else{

        }

    }
}
