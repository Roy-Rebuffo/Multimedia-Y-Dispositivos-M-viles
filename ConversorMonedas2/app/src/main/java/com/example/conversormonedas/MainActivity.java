package com.example.conversormonedas;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Conversión de monedas
    double VDE = 0.719;
    double VDY = 101.615;
    double VDL = 0.596;

    // EditText
    EditText dolarEuroET, dolarYenET, dolarLibraET;
    EditText euroDolarET, euroYenET, euroLibraET;
    EditText yenDolarET, yenEuroET, yenLibraET;
    EditText libraDolarET, libraEuroET, libraYenET;

    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos todos los EditText
        dolarEuroET = findViewById(R.id.dolarEuroET);
        dolarYenET = findViewById(R.id.dolarYenET);
        dolarLibraET = findViewById(R.id.dolarLibraET);

        euroDolarET = findViewById(R.id.euroDolarET);
        euroYenET = findViewById(R.id.euroYenET);
        euroLibraET = findViewById(R.id.euroLibraET);

        yenDolarET = findViewById(R.id.yenDolarET);
        yenEuroET = findViewById(R.id.yenEuroET);
        yenLibraET = findViewById(R.id.yenLibraET);

        libraDolarET = findViewById(R.id.libraDolarET);
        libraEuroET = findViewById(R.id.libraEuroET);
        libraYenET = findViewById(R.id.libraYenET);

        // Agregamos los TextWatcher
        addTextWatcher(dolarEuroET, "dolar");
        addTextWatcher(euroDolarET, "euro");
        addTextWatcher(yenDolarET, "yen");
        addTextWatcher(libraDolarET, "libra");

        // Inicializamos valores
        actualizarConversiones();
    }

    private void addTextWatcher(EditText editText, String moneda) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                isUpdating = true;

                try {
                    double valor = Double.parseDouble(s.toString());
                    switch (moneda) {
                        case "dolar": convertirDesdeDolar(valor); break;
                        case "euro": convertirDesdeEuro(valor); break;
                        case "yen": convertirDesdeYen(valor); break;
                        case "libra": convertirDesdeLibra(valor); break;
                    }
                } catch (NumberFormatException e) {
                    // Input vacío o inválido
                }

                isUpdating = false;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Funciones de conversión
    private void convertirDesdeDolar(double d) {
        // No tocar el campo que se está editando
        if (!dolarEuroET.isFocused()) dolarEuroET.setText(String.format("%.3f", d * VDE));
        if (!dolarYenET.isFocused()) dolarYenET.setText(String.format("%.3f", d * VDY));
        if (!dolarLibraET.isFocused()) dolarLibraET.setText(String.format("%.3f", d * VDL));

        if (!euroDolarET.isFocused()) euroDolarET.setText(String.format("%.3f", d / VDE));
        if (!euroYenET.isFocused()) euroYenET.setText(String.format("%.3f", d / VDE * VDY));
        if (!euroLibraET.isFocused()) euroLibraET.setText(String.format("%.3f", d / VDE * VDL));

        if (!yenDolarET.isFocused()) yenDolarET.setText(String.format("%.3f", d / VDY));
        if (!yenEuroET.isFocused()) yenEuroET.setText(String.format("%.3f", d / VDY * VDE));
        if (!yenLibraET.isFocused()) yenLibraET.setText(String.format("%.3f", d / VDY * VDL));

        if (!libraDolarET.isFocused()) libraDolarET.setText(String.format("%.3f", d / VDL));
        if (!libraEuroET.isFocused()) libraEuroET.setText(String.format("%.3f", d / VDL * VDE));
        if (!libraYenET.isFocused()) libraYenET.setText(String.format("%.3f", d / VDL * VDY));
    }


    private void convertirDesdeEuro(double e) {
        convertirDesdeDolar(e / VDE);
    }

    private void convertirDesdeYen(double y) {
        convertirDesdeDolar(y / VDY);
    }

    private void convertirDesdeLibra(double l) {
        convertirDesdeDolar(l / VDL);
    }

    private void actualizarConversiones() {
        convertirDesdeDolar(1.0); // Por ejemplo, inicializamos 1 dólar
    }
}
