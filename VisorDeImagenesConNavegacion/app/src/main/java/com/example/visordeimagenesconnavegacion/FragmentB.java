package com.example.visordeimagenesconnavegacion;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentB extends Fragment {

    private FragmentInteractionListener mListener;

    public FragmentB() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        Button button = view.findViewById(R.id.btn_go_to_a);
        button.setOnClickListener(v -> {
            // Notificamos a la Activity que queremos volver al Fragmento A (usa popBackStack)
            if (mListener != null) {
                mListener.onNavigate("A");
            }
        });

        return view;
    }

    // Vinculamos y desvinculamos el Listener (igual que en FragmentA)
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}