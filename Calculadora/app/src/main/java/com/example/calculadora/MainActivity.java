package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private enum Entrada{NINGUNA,DIGITO,OPERADOR,CE}
    private Entrada ultimaEntrada;
    private BigDecimal operando1;
    private BigDecimal operando2;
    private char operador;
    private byte numOperandos;
    private boolean puntoDecimal;
    TextView Pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Pantalla =(TextView) findViewById(R.id.pantalla);
        operando1 = new BigDecimal(0);
        operando2 = new BigDecimal(0);
        numOperandos = 0;
        operador = 0;
        puntoDecimal = false;
        ultimaEntrada = Entrada.NINGUNA;
    }
    /**************************************Métodos*********************************************/
    //Tercero
    public void iniciar(View v){
        Pantalla.setText("0.");
        operando1 = new BigDecimal(0);
        operando2 = new BigDecimal(0);
        numOperandos = 0;
        operador = 0;
        puntoDecimal = false;
        ultimaEntrada = Entrada.NINGUNA;
    }
    //Cuarto
    public void borrarEntrada(View v){
        Pantalla.setText("0.");
        puntoDecimal = false;
        ultimaEntrada = Entrada.NINGUNA;
    }
    //Quinto
    public void puntoDec(View v){
        if(ultimaEntrada != Entrada.DIGITO){
            Pantalla.setText("0.");
            ultimaEntrada = Entrada.DIGITO;
        }else if(!puntoDecimal){
            Pantalla.setText(Pantalla.getText().toString() + ".");
        }
        puntoDecimal = true;
    }
    //Sexto

    //Primero
    public void pulsarDigito(View v){
        String t = ((Button)v).getText().toString(); //Coge el texto del boton

        if(ultimaEntrada !=Entrada.DIGITO){
            if(t.compareTo("0") == 0) return;
            Pantalla.setText("");
            ultimaEntrada=Entrada.DIGITO;
            puntoDecimal=false;
        }
        Pantalla.setText(Pantalla.getText().toString() + t);
    }
    //Segundo
    public void operacion(View v){
        String t = ((Button)v).getText().toString(); // coge el texto del boton
        if(numOperandos == 0 && t.equals("-")) ultimaEntrada = Entrada.DIGITO;

        if(ultimaEntrada == Entrada.DIGITO) numOperandos ++;

        if (numOperandos ==1){
            operando1 = BigDecimal.valueOf(Double.valueOf(Pantalla.getText().toString()));
        }else if(numOperandos == 2){
            operando2 = BigDecimal.valueOf(Double.valueOf(Pantalla.getText().toString()));
            switch (operador){
                case '+':
                    operando1= operando1.add(operando2);
                    break;
                case '-':
                    operando1= operando1.subtract(operando2);
                    break;
                case 'x':
                    operando1= operando1.multiply(operando2);
                    break;
                case '÷':
                    operando1= operando1.divide(operando2);
                    break;
                case '=':
                    operando1= operando2;
                    break;
            }
            Pantalla.setText(operando1.toString());
            numOperandos = 1;
        }
        operador = t.charAt(0);
        ultimaEntrada = Entrada.OPERADOR;
    }
}