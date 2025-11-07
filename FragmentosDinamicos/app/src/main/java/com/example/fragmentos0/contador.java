package com.example.fragmentos0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class contador extends Fragment {
    private Button boton;
    private EditText et;
    private TextView tv;
    private static int c = 41;

    public contador() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_contador, container, false);
        /******************************************************************************************/
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        params.addRule(RelativeLayout.BELOW, R.id.boton);

        params.setMargins(0,c,0,0);
        vista.setLayoutParams(params);
        c+=200;
        /******************************************************************************************/

        boton = vista.findViewById(R.id.procesar);
        et = vista.findViewById(R.id.frase);
        tv = vista.findViewById(R.id.contador);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = et.getText().toString();
                tv.setText(String.valueOf(mensaje.length()));
            }
        });
        return vista;
    }
}