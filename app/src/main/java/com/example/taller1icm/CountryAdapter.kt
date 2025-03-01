package com.example.taller1icm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CountryAdapter(
    private val countries: List<Country>,
    private val onClick: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.countryName)
        val flagImageView: ImageView = view.findViewById(R.id.countryFlag) // Nuevo ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.nameTextView.text = country.nombrePais
        holder.itemView.setOnClickListener { onClick(country) }

        // Cargar la imagen con Glide
        Glide.with(holder.itemView.context)
            .load(country.flagUrl)
            .into(holder.flagImageView)
    }

    override fun getItemCount() = countries.size
}
