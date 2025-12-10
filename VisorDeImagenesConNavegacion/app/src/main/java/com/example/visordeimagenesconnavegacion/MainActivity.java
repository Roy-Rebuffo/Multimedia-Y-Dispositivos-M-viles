package com.example.visordeimagenesconnavegacion;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

// 1. La Activity principal implementa la Interfaz de comunicación
public class MainActivity extends AppCompatActivity implements FragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usamos el layout con el FrameLayout
        setContentView(R.layout.activity_main);

        // SOLO se añade el Fragmento A si la Activity se crea por primera vez.
        // Esto evita que se superpongan Fragmentos si la Activity se recrea (ej. rotación).
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new FragmentA())
                    .commit();
        }
    }

    // 2. Implementación del método de la interfaz para manejar la navegación
    @Override
    public void onNavigate(String targetFragment) {
        if (targetFragment.equals("B")) {
            // Lógica para ir de A a B

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Reemplazamos el contenido del contenedor con FragmentB
            transaction.replace(R.id.fragment_container, new FragmentB());

            // AÑADIMOS ESTA TRANSACCIÓN A LA PILA DE RETROCESO.
            // Esto es crucial para que el botón "Atrás" del sistema nos devuelva al Fragmento A.
            transaction.addToBackStack(null);

            transaction.commit();

        } else if (targetFragment.equals("A")) {
            // Lógica para volver de B a A

            // Si el Fragmento B pide volver a A, simplemente deshacemos la última transacción
            // que está en la pila (la de A -> B), gracias a que usamos addToBackStack.
            getSupportFragmentManager().popBackStack();
        }
    }

    // Opcional: Esto no es necesario si usas popBackStack() arriba,
    // pero es la forma alternativa de manejar el botón Atrás
    /*
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    */
}