package com.example.conversion_grados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText gc, gf, gk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gc = findViewById(R.id.centigrados);
        gf = findViewById(R.id.Fahrenheit);
        gk = findViewById(R.id.kelvin);

        gc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                seguirgc();
                return false;
            }
        });

        gf.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                seguirgf();
                return false;
            }
        });

        gk.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                seguirgk();
                return false;
            }
        });
    }

    public void seguirgc() {
        double vgk =redondear(Double.parseDouble(gc.getText().toString()) + 273.15,2);
        double vgf =redondear( (9 * Double.parseDouble(gc.getText().toString()) / 5) + 32,2);
        gk.setText("" + vgk);
        gf.setText("" + vgf);
    }

    public void seguirgf() {
        double vgc =redondear(((Double.parseDouble(gf.getText().toString()) - 32) * 5) / 9,2);
        double vgk = redondear( vgc + 273.15,2);
        gc.setText("" + vgc);
        gk.setText("" + vgk);
    }

    public void seguirgk() {
        double vgc =redondear(Double.parseDouble(gk.getText().toString()) - 273.15,2);
        double vgf =redondear( ((9 * vgc) / 5) + 32,2);
        gc.setText("" + vgc);
        gf.setText("" + vgf);
    }

    public void operar(View vista, EditText ed, int n) {
        double a = Double.parseDouble(ed.getText().toString()) + n;
        ed.setText("" + a);
    }

    public void aumentarCentigrados(View vista) {
        operar(vista, gc, 1);
        seguirgc();
    }

    public void decrementarCentigrados(View vista) {
        operar(vista, gc, -1);
        seguirgc();
    }

    public void aumentarFahrenheit(View vista) {
        operar(vista, gf, 1);
        seguirgf();
    }

    public void decrementarFahrenheit(View vista) {
        operar(vista, gf, -1);
        seguirgf();
    }

    public void aumentarKelvin(View vista) {
        operar(vista, gk, 1);
        seguirgk();
    }

    public void decrementarKelvin(View vista) {
        operar(vista, gk, -1);
        seguirgk();
    }
    public double redondear(double n, int d){
        double ex= Math.pow(10,d);
        return Math.round(n*ex)/ex;
    }
}