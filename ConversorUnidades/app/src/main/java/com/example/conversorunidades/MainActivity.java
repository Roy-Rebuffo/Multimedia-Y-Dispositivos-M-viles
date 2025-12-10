package com.example.conversorunidades;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Datos del JS
    private final String[] UNIDADES = {"longitud", "masa", "capacidad", "superficie", "volumen"};
    private final String[] LONGITUD = {"milímetro", "centímetro", "decímetro", "metro", "decámetro", "hectómetro", "kilómetro"};
    private final String[] MASA = {"miligramo", "centigramo", "decigramo", "gramo", "decagramo", "hectogramo", "kilogramo"};
    private final String[] CAPACIDAD = {"mililitro", "centilitro", "decilitro", "litro", "decalitro", "hectolitro", "kilolitro"};
    private final String[] SUPERFICIE = {"milímetro cuadrado", "centímetro cuadrado", "decímetro cuadrado", "metro cuadrado", "decámetro cuadrado", "hectómetro cuadrado", "kilómetro cuadrado"};
    private final String[] VOLUMEN = {"milímetro cúbico", "centímetro cúbico", "decímetro cúbico", "metro cúbico", "decámetro cúbico", "hectómetro cúbico", "kilómetro cúbico"};

    // Componentes del Layout
    private Spinner spUnidades, spOrigen, spDestino;
    private EditText etOrigen, etDestino;

    // Adaptadores
    private ArrayAdapter<String> adaptadorOrigen, adaptadorDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicializar Vistas
        spUnidades = findViewById(R.id.spUnidades);
        spOrigen = findViewById(R.id.spOrigen);
        spDestino = findViewById(R.id.spDestino);
        etOrigen = findViewById(R.id.etOrigen);
        etDestino = findViewById(R.id.etDestino);

        // 2. Poblar Spinner principal (Categorías)
        ArrayAdapter<String> adaptadorCategorias = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UNIDADES);
        adaptadorCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnidades.setAdapter(adaptadorCategorias);

        // 3. Inicializar Adaptadores de Origen y Destino
        adaptadorOrigen = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new ArrayList<>());
        adaptadorOrigen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigen.setAdapter(adaptadorOrigen);

        adaptadorDestino = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new ArrayList<>());
        adaptadorDestino.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDestino.setAdapter(adaptadorDestino);

        // 4. Configurar Listeners para Spinners

        // Listener principal: Cambia las unidades de Origen/Destino
        spUnidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambioUnidad(UNIDADES[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Listeners para SpOrigen y SpDestino: Recalculan al cambiar la unidad
        AdapterView.OnItemSelectedListener unidadChangeListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Cuando la unidad de Origen o Destino cambia, recalculamos.
                // Asumimos que el usuario quiere calcular de Origen a Destino por defecto.
                calculoGeneral(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spOrigen.setOnItemSelectedListener(unidadChangeListener);
        spDestino.setOnItemSelectedListener(unidadChangeListener);

        // 5. Configurar setOnEditorActionListener para EditText

        // Listener para etOrigen: calcula Destino cuando se pulsa la tecla de acción
        etOrigen.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                // true significa que la entrada es válida
                calculoGeneral(false); // calcular destino desde origen
                return true;
            }
            return false;
        });

        // Listener para etDestino: calcula Origen cuando se pulsa la tecla de acción
        etDestino.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                calculoGeneral(true); // calcular origen desde destino
                return true;
            }
            return false;
        });

        // 6. Simular window.onload: Cargar el estado inicial (Longitud)
        spUnidades.setSelection(0);
        cambioUnidad(UNIDADES[0]);
    }

    /**
     * Implementación de la lógica JS cambio_unidad(ori)
     * Rellena los Spinners de Origen y Destino con las unidades de la categoría seleccionada.
     */
    private void cambioUnidad(String categoria) {
        String[] unidadesSeleccionadas;

        switch (categoria) {
            case "longitud":
                unidadesSeleccionadas = LONGITUD;
                break;
            case "masa":
                unidadesSeleccionadas = MASA;
                break;
            case "capacidad":
                unidadesSeleccionadas = CAPACIDAD;
                break;
            case "superficie":
                unidadesSeleccionadas = SUPERFICIE;
                break;
            case "volumen":
                unidadesSeleccionadas = VOLUMEN;
                break;
            default:
                unidadesSeleccionadas = new String[]{};
                break;
        }

        // 1. Actualizar Adaptadores
        actualizarAdaptador(adaptadorOrigen, unidadesSeleccionadas);
        actualizarAdaptador(adaptadorDestino, unidadesSeleccionadas);

        // 2. Establecer valores por defecto (simulando los indices 6 y 3 del JS)
        spOrigen.setSelection(unidadesSeleccionadas.length > 6 ? 6 : 0);
        spDestino.setSelection(unidadesSeleccionadas.length > 3 ? 3 : 0);

        // 3. Recalcular al cargar las nuevas unidades (Origen -> Destino por defecto)
        calculoGeneral(false);
    }

    /**
     * Función auxiliar para limpiar y rellenar un adaptador
     */
    private void actualizarAdaptador(ArrayAdapter<String> adaptador, String[] nuevasUnidades) {
        adaptador.clear();
        adaptador.addAll(Arrays.asList(nuevasUnidades));
        adaptador.notifyDataSetChanged();
    }

    /**
     * Implementación de la lógica JS calculo_general(ori)
     * Calcula el valor de destino o de origen según el parámetro.
     * @param isCalculatingOriginFromDestination Indica si se calcula ORIGEN usando el valor de DESTINO (true)
     * o si se calcula DESTINO usando el valor de ORIGEN (false).
     */
    private void calculoGeneral(boolean isCalculatingOriginFromDestination) {

        // 1. Obtener valores y manejar errores
        double norigenValue = 0.0;
        double ndestinoValue = 0.0;

        try {
            // Intentar obtener el valor del campo que SÍ se está usando para el cálculo
            if (!isCalculatingOriginFromDestination) {
                norigenValue = Double.parseDouble(etOrigen.getText().toString());
            } else {
                ndestinoValue = Double.parseDouble(etDestino.getText().toString());
            }
        } catch (NumberFormatException e) {
            // Si el campo está vacío o inválido, el valor se mantiene en 0.0, lo que es seguro.
        }

        // 2. Obtener la información de la unidad
        int origenIndex = spOrigen.getSelectedItemPosition();
        int destinoIndex = spDestino.getSelectedItemPosition();

        String unidadDestino = (String) spDestino.getSelectedItem();
        int potencia = 10;

        if (unidadDestino.contains("cuadrado")) {
            potencia = 100;
        } else if (unidadDestino.contains("cúbico")) {
            potencia = 1000;
        }

        // Diferencia de indices (simula la potencia 'n' del JS)
        int n = destinoIndex - origenIndex;

        // 3. Realizar el cálculo
        if (!isCalculatingOriginFromDestination) {
            // CÁLCULO DE ORIGEN A DESTINO
            // Lógica JS: document.getElementById("ndestino").value = document.getElementById("norigen").value / Math.pow(potencia, n);
            double resultado = norigenValue / Math.pow(potencia, n);
            etDestino.setText(String.format("%.2f", resultado));

        } else {
            // CÁLCULO DE DESTINO A ORIGEN
            // Lógica JS: document.getElementById("norigen").value = document.getElementById("ndestino").value / Math.pow(potencia, -n);
            double resultado = ndestinoValue / Math.pow(potencia, -n);
            etOrigen.setText(String.format("%.2f", resultado));
        }
    }
}