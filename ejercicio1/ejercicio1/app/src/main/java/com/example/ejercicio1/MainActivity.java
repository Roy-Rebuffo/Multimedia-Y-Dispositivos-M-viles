package com.example.ejercicio1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static int contador=0;

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
    }
    public void incrementaContador(View vista){
        contador++;
        mostrarResultado();
    }
    public void mostrarResultado(){
        TextView t=(TextView) findViewById(R.id.contador);
        t.setText(String.valueOf(contador));
    }
    public void decrementaContador(View vista){
        contador--;
        mostrarResultado();
    }
    public void resetearContador(View vista){
        contador=0;
        mostrarResultado();
    }


}