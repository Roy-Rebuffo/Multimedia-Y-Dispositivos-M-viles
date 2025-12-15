package com.example.listview2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Persona> listaPersonas;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.list1);

        listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("Maria", 'f'));
        listaPersonas.add(new Persona("Pedro", 'm'));
        listaPersonas.add(new Persona("Laura", 'f'));
        listaPersonas.add(new Persona("Carlos", 'm'));
        listaPersonas.add(new Persona("Ana", 'f'));
        listaPersonas.add(new Persona("Javier", 'm'));
        listaPersonas.add(new Persona("Sofia", 'f'));
        listaPersonas.add(new Persona("David", 'm'));
        listaPersonas.add(new Persona("Elena", 'f'));
        listaPersonas.add(new Persona("Miguel", 'm'));
        listaPersonas.add(new Persona("Isabel", 'f'));

        AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
        lista.setAdapter((ListAdapter) adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,listaPersonas.get(i).getNombre(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}