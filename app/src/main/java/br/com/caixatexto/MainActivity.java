package br.com.caixatexto;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultado1;
    private TextView resultado2;
    private TextView campoNome;
    private TextView campoPeso;
    private TextView campoAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultado1 = findViewById(R.id.textResult1);
        resultado2 = findViewById(R.id.textResult2);
        campoNome = findViewById(R.id.textoNome);
        campoPeso = findViewById(R.id.textoPeso);
        campoAltura = findViewById(R.id.textoAltura);


    }

    public void enviar(View view) {

        String nome = campoNome.getText().toString();
        String pesoStr = campoPeso.getText().toString();
        String alturaStr = campoAltura.getText().toString();

        try {
            Double peso = Double.parseDouble(pesoStr);
            Double altura = Double.parseDouble(alturaStr);

            if (nome.isBlank()||peso <= 0 || altura <= 0) {
                resultado1.setText("ERRO");
                resultado2.setText("ENTRADA INVALIDA");
                return;
            }

            Double imc = peso / (altura * altura);
            DecimalFormat df = new DecimalFormat("#.##");
            String imcFormatado = df.format(imc);

            String classificacao;
            if (imc < 18.5) {
                classificacao = "Baixo peso";
            } else if (imc >= 18.5 && imc <= 24.9) {
                classificacao = "Peso normal";
            } else if (imc >= 25 && imc <= 29.9) {
                classificacao = "Sobrepeso";
            } else if (imc >= 30 && imc <= 34.9) {
                classificacao = "Obesidade grau 1";
            } else if (imc >= 35 && imc <= 39.9) {
                classificacao = "Obesidade grau 2";
            } else {
                classificacao = "Obesidade extrema";
            }

            resultado1.setText(nome);
            resultado2.setText("IMC: " + imcFormatado + " - " + classificacao);

        } catch (NumberFormatException e) {
            resultado1.setText("ERRO");
            resultado2.setText("ENTRADA INVALIDA");
        }
    }

    public void limpar(View view) {
        resultado1.setText("");
        resultado2.setText("");
    }
}
