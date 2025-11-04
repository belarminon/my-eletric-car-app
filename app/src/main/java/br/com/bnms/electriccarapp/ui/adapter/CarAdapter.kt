package br.com.bnms.electriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.bnms.electriccarapp.R
import androidx.recyclerview.widget.RecyclerView
import br.com.bnms.electriccarapp.domain.CarroDomain


class CarAdapter (private val carros: List<CarroDomain>):
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    // Cria uma nova view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }

    // Pega o conteúdo da view e troca pela informacao de item de uma lista
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val cars = carros[position]

        holder.preco.text = cars.preco
        holder.bateria.text = cars.bateria
        holder.potencia.text = cars.potencia
        holder.tempoRecarga.text = cars.tempoRecarga
        //holder.urlPhoto.setImageResource( cars.urlPhoto )
    }

    // Pega a quantidade de carros da lista
    override fun getItemCount(): Int = carros.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView = view.findViewById(R.id.tv_price_value)
        val bateria: TextView = view.findViewById(R.id.tv_batery_value)
        val potencia: TextView = view.findViewById(R.id.tv_power_value)
        val tempoRecarga: TextView = view.findViewById(R.id.tv_reload_value)
        // val urlPhoto: ImageView = view.findViewById(R.id.iv_car)
    }
}