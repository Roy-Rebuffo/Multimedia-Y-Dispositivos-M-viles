package com.example.operaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Operaciones2 extends AppCompatActivity {

    TextView suma,resta,mult,division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        suma = findViewById(R.id.suma);
        resta = findViewById(R.id.resta);
        mult = findViewById(R.id.mult);
        division = findViewById(R.id.division);

        Bundle b = getIntent().getExtras();

        double primero = Double.parseDouble(b.getString("primero"));
        double segundo = Double.parseDouble(b.getString("segundo"));

        suma.setText("Suma: " + (primero + segundo));
        resta.setText("Resta: " + (primero - segundo));
        mult.setText("Multiplicación: " + (primero * segundo));
        division.setText("División: " + (primero / segundo));
    }

    public void salir(View vista){
        finish();
    }
}