package com.example.conversormonedas;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText mainInput;
    TextView resDolar, resEuro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainInput = findViewById(R.id.iptConverter);

        resDolar = findViewById(R.id.resDolar);     // resultado (output)
        resEuro = findViewById(R.id.resEuro);       // resultado (output)

        }
    public void incrementar(View vista){
        Double n = Double.valueOf(mainInput.getText().toString())+1;
        mainInput.setText(String.valueOf(n));

    }
    public void decrementar(View vista){
        Double n = Double.valueOf(mainInput.getText().toString())-1;
        mainInput.setText(String.valueOf(n));

    }

    public void convertir(View v) {
        String sBase = mainInput.getText().toString();
        if (sBase.isEmpty()) return; // o mostrar error

        double cantidadBase = Double.parseDouble(sBase);

        double valorDolar = cantidadBase * 0.86;
        double valorEuro  = cantidadBase * 1.17;

        // Mostramos los resultados en las vistas de resultado
        resDolar.setText(String.format("%.2f", valorDolar));
        resEuro.setText(String.format("%.2f", valorEuro));
    }
}