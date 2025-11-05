//Poner una caja de texto donde metamos un primer nº
//Otra caja de texto donde metamos un 2º nº
//Un boton enviar a donde nos lleve a una segunda acitvidad donde le pasemos los 2 nº introducidos
//En esta segunda actividad nos tiene que mostrar la +-*/ de los 2 nº pasados
//y un boton salir

package com.example.operaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

        EditText primerNumero,segundoNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        primerNumero = findViewById(R.id.primero);
        segundoNumero = findViewById(R.id.segundo);
    }

    public void enviar(View view){
        //Recogemos los nº y los enviamos al segundo ejercicio
        String p = primerNumero.getText().toString();
        String s = segundoNumero.getText().toString();

        Intent i = new Intent(this, Operaciones2.class);
        //Enviamos los nº al segundo ejercicio
        i.putExtra("primero", p);
        i.putExtra("segundo", s);

        startActivity(i);
    }
}