package com.example.recyclerviewfutbolv2

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    var rva: RecyclerView.Adapter<*>? = null
    var lm: RecyclerView.LayoutManager? = null
    var equipos: MutableList<Equipo> = ArrayList()
    var listaReserva: MutableList<Equipo> = ArrayList()

    var indiceActual = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Leemos los datos
        readData()


        // Enlazamos la interfaz gráfica del RecyclerView con el código
        rv = findViewById(R.id.rv)
        // Establecemos que los elementos del RecyclerView se apilen verticalmente
        lm = LinearLayoutManager(this)
        // Creamos un adaptador para el RecyclerView
        rva = Adaptador(this, equipos)
        // Enlazamos el adaptador con el objeto RecyclerView
        rv.setAdapter(rva)
        rv.setLayoutManager(lm)
    }

    // Añadimos un nuevo equipo al RecyclerView
    fun addElemento(v: View?) {

        if (indiceActual < listaReserva.size) {

            val e = listaReserva[indiceActual]
            equipos.add(e)

            rva!!.notifyItemInserted(equipos.size - 1)

            indiceActual++
        }
    }


    // Leemos los datos de las montañas del fichero arrays.xml
    fun readData() {
        val res = resources
        val nombres = res.getStringArray(R.array.nombres)
        val region = res.getString(R.string.region)
        val pais = res.getString(R.string.pais)
        val URLS = res.getStringArray(R.array.URLS)
        val fotos = res.obtainTypedArray(R.array.fotos)

        for (i in nombres.indices) {
            val e = Equipo(
                nombres[i],
                region,
                pais,
                fotos.getResourceId(i, R.drawable.alaves),
                Uri.parse(URLS[i])
            )
            listaReserva.add(e)
        }

        fotos.recycle()
    }

}