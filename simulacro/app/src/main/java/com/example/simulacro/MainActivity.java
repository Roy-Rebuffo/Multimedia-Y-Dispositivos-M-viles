package com.example.simulacro;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText p,s;
    ListView l;
    TextView r;
    String [] operaciones = {"+", "-", "*", "/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        p = findViewById(R.id.primero);
        s = findViewById(R.id.segundo);
        l = findViewById(R.id.lista);
        r = findViewById(R.id.resultado);

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,operaciones);
        l.setAdapter(adaptador);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                double n1 = Double.parseDouble(p.getText().toString());
                double n2 = Double.parseDouble(s.getText().toString());

                String operacion = operaciones[position];

                double resultado = 0;

                switch (operacion) {
                    case "+":
                        resultado = sumar(n1, n2);
                        break;
                    case "-":
                        resultado = restar(n1, n2);
                        break;
                    case "*":
                        resultado =redondear(mult(n1, n2),2);
                        break;
                    case "/":
                        resultado = redondear(dividir(n1, n2),2);
                        break;
                }

                r.setText("= " + resultado);
            }
        });

    }
    public double sumar(double p, double s){
        return p + s;
    }
    public double restar(double p, double s){
        return p - s;
    }
    public double mult(double p, double s){
        return p * s;
    }
    public double dividir(double p, double s){
        return p / s;
    }
    public double redondear(double n, int d){
        double ex= Math.pow(10,d);
        return Math.round(n*ex)/ex;
    }
}