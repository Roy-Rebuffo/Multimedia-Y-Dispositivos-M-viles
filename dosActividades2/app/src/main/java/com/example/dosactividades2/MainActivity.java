package com.example.dosactividades2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1,usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuario);
        et1 = findViewById(R.id.contraseña);

    }
    public void verificar(View vista){
        String cadena = et1.getText().toString();
        String usu = usuario.getText().toString();
        if (cadena.equals("abc123") && usu.equalsIgnoreCase("Royzao")){
            Intent i = new Intent(this,MainActivity2.class);
            // hace que pasamos a la intencion (i) como parámetro "usuario" que recibira el nombre del usuario
            i.putExtra("usuario", usu);
            startActivity(i);
        }else{
            Toast.makeText(this,"Clave incorrecta",Toast.LENGTH_SHORT).show();

            //Toast notificacion = Toast.makeText(this,"Claver incorrecta",Toast.LENGTH_SHORT);
            //notificacion.show();
        }
    }
}