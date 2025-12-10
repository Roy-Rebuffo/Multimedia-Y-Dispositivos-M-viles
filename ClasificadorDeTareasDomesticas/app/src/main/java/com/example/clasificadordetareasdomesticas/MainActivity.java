package com.example.clasificadordetareasdomesticas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    ListView lista;
    String [] categoriasSpinner = {"COCINA","LIMPIEZA","JARDINERÍA"};
    String cat;
    String [] tareasCocina = {"Lavar platos", "Hacer la cena", "Limpiar nevera"};
    String [] tareasLimpieza = {"Barrer", "Fregar suelos", "Quitar el polvo"};
    String [] tareasJardineria = {"Regar plantas", "Podar setos", "Cortar césped"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sp = findViewById(R.id.spinner);
        lista = findViewById(R.id.list);

        //ArrayAdapter para poblar el Spinner
        ArrayAdapter<String>adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,categoriasSpinner);
                sp.setAdapter(adaptador);
        //Usar el Spinner
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = categoriasSpinner[position];
                cambio();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        }
    //ArrayAdapter para poblar el ListView
    public void poblarTareasCocina(){
        ArrayAdapter<String> adaptadorLista = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tareasCocina);
        lista.setAdapter(adaptadorLista);
    }
    public void poblarTareasLimpieza(){
        ArrayAdapter<String> adaptadorLista = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,tareasLimpieza);
        lista.setAdapter(adaptadorLista);
    }
    public void poblarTareasJardineria(){
        ArrayAdapter<String> adaptadorLista = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,tareasJardineria);
        lista.setAdapter(adaptadorLista);
    }
    public void cambio(){
        switch (cat){
            case "COCINA":
                poblarTareasCocina();
                break;
            case "LIMPIEZA":
                poblarTareasLimpieza();
                break;
            case "JARDINERÍA":
                poblarTareasJardineria();
                break;
        }
    }
}