package com.example.recyclerviewfutbolv2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador (

    var c: Context, var equipos: MutableList<Equipo>
) : RecyclerView.Adapter<Adaptador.vh>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        val li = LayoutInflater.from(c)
        val v = li.inflate(R.layout.rv_row, parent, false)
        return vh(v)
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        holder.nombre.text = equipos[position].nombre
        holder.continente.text = "Region: " + equipos[position].region
        holder.pais.text = "País: " + equipos[position].pais
        holder.foto.setImageResource(equipos[position].foto)
        val m = equipos[position]

        // onclick para ir a la página de la Wikipedia
        holder.masInfo.setOnClickListener {
            val launchBrowser = Intent(Intent.ACTION_VIEW, m.masInfo)
            c.startActivity(launchBrowser)
        }

        // onclick para borrar el monte
        holder.borrar.setOnClickListener {
            equipos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, equipos.size)
        }
    }
    // Devolver el número de elementos que componen nuestra RecyclerView
    override fun getItemCount(): Int {
        return equipos.size
    }

    /*
        Clase encargada de enlazar los elementos de la interfaz gráfica con las variables del
        adaptador
    */
    inner class vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var continente: TextView
        var pais: TextView
        var masInfo: TextView
        var foto: ImageView
        var borrar: ImageButton

        init {
            nombre = itemView.findViewById<View>(R.id.row_name) as TextView
            continente = itemView.findViewById<View>(R.id.row_continent) as TextView
            pais = itemView.findViewById<View>(R.id.row_country) as TextView
            masInfo = itemView.findViewById<View>(R.id.row_info) as TextView
            foto = itemView.findViewById<View>(R.id.row_img) as ImageView
            borrar = itemView.findViewById<View>(R.id.row_delete) as ImageButton
        }
    }

}