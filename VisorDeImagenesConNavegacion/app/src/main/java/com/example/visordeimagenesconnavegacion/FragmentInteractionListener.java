package com.example.visordeimagenesconnavegacion;

public interface FragmentInteractionListener {
    // Método llamado por los Fragmentos para solicitar un cambio de pantalla.
    void onNavigate(String targetFragment);
}