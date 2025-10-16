package com.example.temperaturas;

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

    EditText jtgc, jtgf,jtgk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        jtgc = (EditText) findViewById(R.id.centigrados);
        jtgf = (EditText) findViewById(R.id.fahrenheit);
        jtgk = (EditText) findViewById(R.id.kelvin);

        jtgc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                general(jtgc,0);
                return false;
            }
        });
        jtgf.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                general(jtgf,0);
                return false;
            }
        });
        jtgk.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                general(jtgk,0);
                return false;
            }
        });
    }

    public void decrementarFahrenheit(View vista){
        general(jtgf, -1);
    }

    public void incrementarFahrenheit(View vista){
       general(jtgf, +1);
    }

    public void decrementarKelvin(View vista){
       general(jtgk,-1);
    }
    public void incrementarKelvin(View vista){
        general(jtgk,+1);
    }

    public void decrementarCentigrados(View vista){
        general(jtgc, -1);
    }
    public void incrementarCentigrados(View vista){
        general(jtgc, +1);
    }

    public void general(EditText t, int a){
        double n =Double.valueOf(jtgc.getText().toString())+a;
        jtgc.setText(String.valueOf(n));

        if(t == jtgc){
            jtgf.setText(String.valueOf((9 * n) / 5 + 32));
            jtgk.setText(String.valueOf(n + 273.15));
        } else if (t ==jtgf) {
            jtgc.setText(String.valueOf((5 * (n - 32)) / 9));
            jtgk.setText(String.valueOf(((5 * (n - 32)) / 9)) + 273.15);
        }else if (t == jtgk){
            jtgc.setText(String.valueOf(n - 273.15));
            jtgf.setText(String.valueOf(    9*((n - 273.15))/5+32   ));
        }
    }
}