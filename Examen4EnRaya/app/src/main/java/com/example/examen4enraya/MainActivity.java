package com.example.examen4enraya;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private int[] estadoTablero;
    private ImageView[] casillas = new ImageView[16];
    private int turno;
    private boolean juegoTerminado;
    TableLayout reji;
    Button iniciar;
    private TextView textoTurno;
    private Button botonIniciar;
    private TextView titulo;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Enlazar vistas
        textoTurno = findViewById(R.id.turno);
        reji = findViewById(R.id.tableLayout);
        botonIniciar = findViewById(R.id.boton_iniciar);
        titulo = findViewById(R.id.titulo);

        // Configurar el tablero
        int index = 0;
        for (int i = 0; i < reji.getChildCount(); i++) {
            View v = reji.getChildAt(i);
            if (v instanceof TableRow) {
                TableRow fila = (TableRow) v;
                for (int j = 0; j < fila.getChildCount(); j++) {
                    View v2 = fila.getChildAt(j);
                    if (v2 instanceof ImageView) {
                        final int pos = index;
                        casillas[index] = (ImageView) v2;
                        casillas[index].setTag(pos);
                        casillas[index].setOnClickListener(view -> tocarCasilla(view));
                        index++;
                    }
                }
            }
        }

        // Iniciar el juego por primera vez
        iniciarJuego();
    }
    private void tocarCasilla(View view) {
        // Si el juego ha terminado, no hacer nada
        if (juegoTerminado) return;

        ImageView casillaPulsada = (ImageView) view;
        int pos = (int) casillaPulsada.getTag();

        // Comprobar si la casilla ya está ocupada
        if (estadoTablero[pos] != 0) {
            Toast.makeText(this, "Casilla ocupada", Toast.LENGTH_SHORT).show();
            return;
        }

        estadoTablero[pos] = turno;
        // Evitar que se pueda volver a pulsar
        casillaPulsada.setClickable(false);

        botonIniciar.setText("Juego Iniciado");
        // Cambiar la imagen y el turno
        if (turno == 1) {
            casillaPulsada.setImageResource(R.drawable.p1);
            turno = 2;
            textoTurno.setText("2");
        } else {
            casillaPulsada.setImageResource(R.drawable.p2);
            turno = 1;
            textoTurno.setText("1");
        }

        // Comprobar si hay un ganador o empate
        comprobarGanador();
    }

    //Comprueba el estado del juego para ver si hay un ganador o empate.
    private void comprobarGanador() {
        for (int[] combinacion : combinacionesGanadoras) {
            if (estadoTablero[combinacion[0]] == estadoTablero[combinacion[1]] &&
                    estadoTablero[combinacion[1]] == estadoTablero[combinacion[2]] &&
                    estadoTablero[combinacion[2]] == estadoTablero[combinacion[3]] &&
                    estadoTablero[combinacion[0]] != 0) {

                juegoTerminado = true;
                String mensaje = "¡Ha ganado el Jugador " + estadoTablero[combinacion[0]] + "!";
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                desactivarTablero();
                botonIniciar.setText("Reiniciar");
                textoTurno.setText("");
                titulo.setText(mensaje);
                botonIniciar.setOnClickListener(view -> iniciarJuego());
                return;
            }
        }

        // 2. Si no hay ganador, comprobar si hay empate
        boolean hayEmpate = true;
        for (int estado : estadoTablero) {
            if (estado == 0) {
                hayEmpate = false;
                break;
            }
        }

        if (hayEmpate) {
            juegoTerminado = true;
            Toast.makeText(this, "¡Empate!", Toast.LENGTH_LONG).show();
        }
    }
    private void desactivarTablero() {
        for (ImageView casilla : casillas) {
            casilla.setClickable(false);
        }
    }
    private void iniciarJuego() {
        textoTurno.setText("");
        titulo.setText("Turno de Jugador: ");
        botonIniciar.setText("Iniciar Juego");

        turno = 1; // Empieza el jugador 1
        estadoTablero = new int[16];
        juegoTerminado = false;
        textoTurno.setText("1");

        // Limpiamos visualmente el tablero
        for (ImageView casilla : casillas) {
            if (casilla != null) {
                casilla.setImageResource(R.drawable.joker);
                casilla.setClickable(true);
            }
        }
    }
}