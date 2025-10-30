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
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private enum Entrada{NINGUNA,DIGITO,OPERADOR,CE}
    private Entrada ultimaEntrada;
    private BigDecimal operando1;
    private BigDecimal operando2;
    private char operador; // + - * /
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
    /**************************************Operaciones*****************************************/

    //Tercero
    public void iniciar(View v){ //Borra la calculadora e inicia desde 0
        Pantalla.setText("0.");
        operando1 = new BigDecimal(0);
        operando2 = new BigDecimal(0);
        numOperandos = 0;
        operador = 0;
        puntoDecimal = false;
        ultimaEntrada = Entrada.NINGUNA;
    }
    //Cuarto
    public void borrarEntrada(View v){ //Borra el operando que se esté usando en el momento
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
        //Si pone una vez al punto se queda en true y ya no puede volver a dar otra vez al punto
    }
    //Sexto
    public void tantoPorciento(View v){
        BigDecimal resultado;
        if(ultimaEntrada == Entrada.DIGITO){
            resultado = BigDecimal.valueOf(Double.valueOf(Pantalla.getText().toString())).divide(new BigDecimal(100));
            resultado = operando1.multiply(resultado);
            Pantalla.setText(resultado.toString());
            //Siguiente operación
            operando1 = resultado;
            numOperandos = 1;
            ultimaEntrada= Entrada.NINGUNA;
            puntoDecimal = false;
        }
    }
    /**************************************Fin Operaciones************************************/

    /**************************************Lector teclas**************************************/
    //Primero
    public void pulsarDigito(View v){
        String t = ((Button)v).getText().toString(); //Coge el texto del boton

        if(ultimaEntrada !=Entrada.DIGITO){ // si la ultima entrada no es un digito
            if(t.compareTo("0") == 0) return;
            Pantalla.setText("");//borra el 0 del principio del programa y lo sustituye por el tecleado
            ultimaEntrada=Entrada.DIGITO; // añade el digito
            puntoDecimal=false;
        }
        Pantalla.setText(Pantalla.getText().toString() + t); //si vulve a añadir un digito se concatena
    }
    /*****************************Fin de Lector teclas****************************************/
    //Segundo
    public void operacion(View v){ // + - * / =
        String t = ((Button)v).getText().toString(); // coge el texto del boton
        if(numOperandos == 0 && t.equals("-")) ultimaEntrada = Entrada.DIGITO;

        if(ultimaEntrada == Entrada.DIGITO) numOperandos ++;

        if (numOperandos ==1){
            operando1 = BigDecimal.valueOf(Double.valueOf(Pantalla.getText().toString())); // coge lo que tiene en pantalla
        }else if(numOperandos == 2){
            operando2 = BigDecimal.valueOf(Double.valueOf(Pantalla.getText().toString())); // coge lo que tiene en pantalla
            switch (operador){
                case '+':
                    operando1= operando1.add(operando2); // add = suma
                    break;
                case '-':
                    operando1= operando1.subtract(operando2); // substract = resta
                    break;
                case 'x':
                    operando1= operando1.multiply(operando2); // multiply = multiplicar
                    break;
                case '÷':
                    operando1 = operando1.divide(operando2, 2, RoundingMode.HALF_DOWN);
                    break;
                case '=':
                    operando1 = operando2;
                    break;
            }
            Pantalla.setText(operando1.toString());
            numOperandos = 1;
        }
        // cuando le da al operador (+-*/) lo tiene que poner en 0 para ir otra vez al metodo
        // de pulsarDigito, ya que ese metodo empieza cuando el texto es = 0 y luego meteria el operando2 y haria lo mismo
        operador = t.charAt(0);
        ultimaEntrada = Entrada.OPERADOR;
    }
}