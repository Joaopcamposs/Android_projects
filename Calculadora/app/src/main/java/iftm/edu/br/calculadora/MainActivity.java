package iftm.edu.br.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt_visor;
    private TextView txt_hist;

    boolean igual;
    boolean opAtiva;
    boolean decAtivo;
    double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txt_visor = findViewById(R.id.txt_visor);
        this.txt_hist = findViewById(R.id.txt_hist);
    }

    //ao clicar em valores
    public void OnClickValue(View view){
        Button btn = (Button)view;
        if(!txt_visor.getText().toString().equalsIgnoreCase("Não é possível dividir por zero")) {
            if (!igual)//se igual foi a ultima operacao realizada
                txt_visor.append(btn.getText().toString());
            else {
                txt_visor.setText("");
                igual = false;
                txt_visor.append(btn.getText().toString());
            }
        }
    }

    public void OnClickClear(View view){
        txt_visor.setText("");
        txt_hist.setText("");
        igual=false;
        opAtiva=false;
        decAtivo=false;
    }

    public void OnClickDecimal(View view){
        Button btn = (Button)view;

        if(!txt_visor.getText().toString().equalsIgnoreCase("Não é possível dividir por zero")) {
            if (!decAtivo && !txt_visor.getText().toString().contains(".")) {
                decAtivo = true;
                txt_visor.append(btn.getText().toString());
            }
        }
    }

    public void OnClickOperation(View view){
        Button btn = (Button)view;
        String op = btn.getText().toString();

        if(!txt_visor.getText().toString().equalsIgnoreCase("Não é possível dividir por zero")){
        switch (op){
                case "+":
                    if (txt_hist.getText().toString().isEmpty()) {//se o historico estiver vazio
                        valor = Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(txt_visor.getText().toString());
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else if (txt_visor.getText().toString().isEmpty()) {//historico nao vazio, mas visor vazio (troca de sinal)
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else {//historico e visor nao vazios
                        valor += Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    }
                    decAtivo = false;
                    break;

                case "-":
                    if (txt_hist.getText().toString().isEmpty()) {//se o historico estiver vazio
                        valor = Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(txt_visor.getText().toString());
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else if (txt_visor.getText().toString().isEmpty()) {//historico nao vazio, mas visor vazio (troca de sinal)
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else {//historico e visor nao vazios
                        valor -= Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    }
                    decAtivo = false;
                    break;

                case "X":
                    if (txt_hist.getText().toString().isEmpty()) {//se o historico estiver vazio
                        valor = Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(txt_visor.getText().toString());
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else if (txt_visor.getText().toString().isEmpty()) {//historico nao vazio, mas visor vazio (troca de sinal)
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else {//historico e visor nao vazios
                        valor *= Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    }
                    decAtivo = false;
                    break;

                case "/":
                    if (txt_hist.getText().toString().isEmpty()) {//se o historico estiver vazio
                        valor = Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText(txt_visor.getText().toString());
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else if (txt_visor.getText().toString().isEmpty()) {//historico nao vazio, mas visor vazio (troca de sinal)
                        txt_hist.setText(Double.toString(valor));
                        txt_hist.append(btn.getText().toString());
                        txt_visor.setText("");
                    } else {//historico e visor nao vazios
                        if (Double.parseDouble(txt_visor.getText().toString()) != 0) {
                            valor /= Double.parseDouble(txt_visor.getText().toString());
                            txt_hist.setText(Double.toString(valor));
                            txt_hist.append(btn.getText().toString());
                            txt_visor.setText("");
                        } else {
                            txt_visor.setText("Não é possível dividir por zero");
                        }
                    }
                    decAtivo = false;
                    break;

                case "=":
                    if (txt_hist.getText().toString().contains("+")) {
                        valor += Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText("");
                        txt_visor.setText(Double.toString(valor));
                    } else if (txt_hist.getText().toString().contains("-")) {
                        valor -= Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText("");
                        txt_visor.setText(Double.toString(valor));
                    } else if (txt_hist.getText().toString().contains("X")) {
                        valor *= Double.parseDouble(txt_visor.getText().toString());
                        txt_hist.setText("");
                        txt_visor.setText(Double.toString(valor));
                    } else if (txt_hist.getText().toString().contains("/")) {
                        if(Double.parseDouble(txt_visor.getText().toString()) != 0) {
                            valor /= Double.parseDouble(txt_visor.getText().toString());
                            txt_hist.setText("");
                            txt_visor.setText(Double.toString(valor));
                        }else{txt_visor.setText("Não é possível dividir por zero");}
                    }

                    decAtivo = false;
                    igual = true;
                    break;
            }
        }
    }
}
