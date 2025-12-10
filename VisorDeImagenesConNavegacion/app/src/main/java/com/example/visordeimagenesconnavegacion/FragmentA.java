package com.example.visordeimagenesconnavegacion;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentA extends Fragment {

    private FragmentInteractionListener mListener;

    public FragmentA() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        Button button = view.findViewById(R.id.btn_go_to_b);
        button.setOnClickListener(v -> {
            // Notificamos a la Activity que queremos ir al Fragmento B
            if (mListener != null) {
                mListener.onNavigate("B");
            }
        });

        return view;
    }

    // Vinculamos el Listener a la Activity cuando el Fragmento se adjunta
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentInteractionListener");
        }
    }

    // Desvinculamos el Listener cuando el Fragmento se desadjunta
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}