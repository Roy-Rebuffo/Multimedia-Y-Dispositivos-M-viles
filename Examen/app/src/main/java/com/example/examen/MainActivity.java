package com.example.examen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private int turno;
    private int[] estadoTablero;
    private boolean juegoTerminado;
    private TextView textoTurno;
    private ImageView[] casillas;
    private Button botonReiniciar;

    private final int[][] combinacionesGanadoras = {
            // Filas
            {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15},
            // Columnas
            {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15},
            // Diagonales
            {0, 5, 10, 15}, {3, 6, 9, 12}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoTurno = findViewById(R.id.turno);
        botonReiniciar = findViewById(R.id.boton_reiniciar);

        casillas = new ImageView[16];
        for (int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("numero_" + i, "id", getPackageName());
            casillas[i] = findViewById(resID);

            casillas[i].setTag(i);

            casillas[i].setOnClickListener(this::tocarCasilla);
        }

        botonReiniciar.setOnClickListener(v -> iniciarJuego());

        iniciarJuego();
    }

    /*Prepara o reinicia el estado del juego.*/
    private void iniciarJuego() {
        turno = 1;
        estadoTablero = new int[16]; // Array de 16 ceros
        juegoTerminado = false;

        textoTurno.setText("1");

        // Limpiamos visualmente el tablero
        for (ImageView casilla : casillas) {
            casilla.setImageResource(R.drawable.joker);
            casilla.setClickable(true);
        }
    }
    private void tocarCasilla(View view) {
        if (juegoTerminado) return;

        ImageView casillaPulsada = (ImageView) view;
        int posicion = (int) casillaPulsada.getTag();

        if (estadoTablero[posicion] != 0) {
            Toast.makeText(this, "Casilla ya ocupada", Toast.LENGTH_SHORT).show();
            return;
        }

        estadoTablero[posicion] = turno;
        casillaPulsada.setClickable(false);

        if (turno == 1) {
            casillaPulsada.setImageResource(R.drawable.p1);
            turno = 2;
            textoTurno.setText("2");
        } else {
            casillaPulsada.setImageResource(R.drawable.p2);
            turno = 1;
            textoTurno.setText("1");
        }

        comprobarGanador();
    }

    /*Comprueba si hay un ganador o si el juego ha terminado en empate.*/
    private void comprobarGanador() {
        // 1. Buscar un ganador
        for (int[] combinacion : combinacionesGanadoras) {
            if (estadoTablero[combinacion[0]] == estadoTablero[combinacion[1]] &&
                    estadoTablero[combinacion[1]] == estadoTablero[combinacion[2]] &&
                    estadoTablero[combinacion[2]] == estadoTablero[combinacion[3]] &&
                    estadoTablero[combinacion[0]] != 0) {

                juegoTerminado = true;
                String mensaje = "¡Ha ganado el Jugador " + estadoTablero[combinacion[0]] + "!";
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                desactivarTablero();
                return; // Salimos, ya hay ganador
            }
        }

        boolean esEmpate = true;
        for (int estado : estadoTablero) {
            if (estado == 0) {
                esEmpate = false;
                break;
            }
        }

        if (esEmpate) {
            juegoTerminado = true;
            Toast.makeText(this, "¡Es un empate!", Toast.LENGTH_LONG).show();
        }
    }
    private void desactivarTablero() {
        for (ImageView casilla : casillas) {
            casilla.setClickable(false);
        }
    }
}
