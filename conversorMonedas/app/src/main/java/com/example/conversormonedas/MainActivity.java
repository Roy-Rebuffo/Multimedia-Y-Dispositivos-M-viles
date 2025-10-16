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

    EditText mainInput, dolar, euro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainInput = (EditText) findViewById(R.id.iptConverter); // Caja de arriba
        dolar = (EditText) findViewById(R.id.iptDolar);
        euro = (EditText) findViewById(R.id.iptEuro);

        }
    public void incrementar(View vista){
        Double n = Double.valueOf(mainInput.getText().toString())+1;
        mainInput.setText(String.valueOf(n));
    }
    public void decrementar(View vista){
        Double n = Double.valueOf(mainInput.getText().toString())-1;
        mainInput.setText(String.valueOf(n));
    }

    public void general(EditText t, int a){
        Double n = Double.valueOf(dolar.getText().toString()) * a;

    }
}