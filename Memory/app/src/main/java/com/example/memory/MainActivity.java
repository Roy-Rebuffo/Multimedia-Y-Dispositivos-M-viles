package com.example.memory;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TableLayout reji;
    private int[] posiciones = new int[16]; // valor de las cartas (1..8)
    private ImageView[] casillas = new ImageView[16]; // referencias a ImageView
    private int numero1 = -1;
    private ImageView carta1;
    private boolean turno = true; // true = primera carta
    private int coincidencias = 0;
    private TextView contador;
    private Handler handler = new Handler();

    // array de drawables: detective + cartas 1 a 8
    private int[] imgs = {
            R.drawable.detective,
            R.drawable.numero_1,
            R.drawable.numero_2,
            R.drawable.numero_3,
            R.drawable.numero_4,
            R.drawable.numero_5,
            R.drawable.numero_6,
            R.drawable.numero_7,
            R.drawable.numero_8
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        contador = findViewById(R.id.contador);
        Arrays.fill(posiciones, 0);
        aleatorio();

        reji = findViewById(R.id.tableLayout);

        int index = 0;

        // recorrer tabla y asignar listeners
        for (int i = 0; i < reji.getChildCount(); i++) {
            View v = reji.getChildAt(i);
            if (v instanceof TableRow) {
                TableRow fila = (TableRow) v;
                for (int j = 0; j < fila.getChildCount(); j++) {
                    View v2 = fila.getChildAt(j);
                    if (v2 instanceof ImageView) {
                        final int pos = index;
                        casillas[index] = (ImageView) v2;

                        casillas[index].setImageResource(R.drawable.detective);

                        casillas[index].setOnClickListener(view -> voltearCarta(pos));

                        index++;
                    }
                }
            }
        }
    }

    private void voltearCarta(int pos) {
        ImageView carta = casillas[pos];
        int valor = posiciones[pos];

        // mostrar la carta
        carta.setImageResource(imgs[valor]);

        if (turno) {
            // primera carta
            numero1 = valor;
            carta1 = carta;
        } else {
            // segunda carta
            if (valor == numero1) {
                // coincidencia
                coincidencias++;
                contador.setText(String.valueOf(coincidencias));
                // desactivar click
                carta1.setEnabled(false);
                carta.setEnabled(false);
            } else {
                // no coincide, ocultar ambas tras delay
                handler.postDelayed(() -> {
                    carta1.setImageResource(R.drawable.detective);
                    carta.setImageResource(R.drawable.detective);
                }, 500);
            }
        }

        turno = !turno;
    }

    // mezcla cartas
    private void aleatorio() {
        boolean colocado;
        int n;
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 2; j++) {
                colocado = true;
                while (colocado) {
                    n = alea(0, 15);
                    if (posiciones[n] == 0) {
                        posiciones[n] = i;
                        colocado = false;
                    }
                }
            }
        }
    }

    private int alea(int li, int ls) {
        return (int) (Math.random() * (ls - li + 1) + li);
    }
}
