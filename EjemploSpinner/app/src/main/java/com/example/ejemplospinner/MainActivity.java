package com.example.ejemplospinner;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText n1,n2, res;
    Spinner sp;
    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.numero1);
        n2 = findViewById(R.id.numero2);
        res = findViewById(R.id.resultado);
        sp = findViewById(R.id.spinner);

        //COGE EL ARRAY QUE TENEMOS EN values/themes/strings.xml
        String[] matriz_operaciones = getResources().getStringArray(R.array.operaciones);
        String[] oprcs = {"+","-","*","/"};
        op = "suma";

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, matriz_operaciones);
        sp.setAdapter(adaptador);

        //USAR EL SPINNER
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                op = oprcs[position]; //LIT LO QUE CONTIENE EL ARRAY DE ("+","-","*","/")
                cambio();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        n1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                cambio();
                return false;
            }
        });
        n2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                cambio();
                return false;
            }
        });
    }
    public void cambio(){
        double rs = 0.0;
        double v1 = Double.parseDouble(n1.getText().toString());
        double v2 = Double.parseDouble(n2.getText().toString());

        //OP es lo que cogemos del array arriba
        rs = op.equals("+") ? v1 + v2 : op.equals("-") ? v1 - v2 : op.equals("*") ? v1 * v2 : op.equals("/") ? v1 / v2 : 0.0;
        //RES es el resultado
        res.setText(String.format("%.2f",rs));
    }
}