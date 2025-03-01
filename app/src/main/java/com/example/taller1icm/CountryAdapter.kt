package com.example.taller1icm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//data class Country(val nombrePais: String, val capital: String, val sigla: String)

class CountryAdapter(
    private val countries: List<Country>,
    private val onClick: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.countryName)
        val flagImageView: ImageView = view.findViewById(R.id.countryFlag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.nameTextView.text = country.nombrePais
        Glide.with(holder.itemView.context)
            .load(country.flagUrl)
            .into(holder.flagImageView)

        holder.itemView.setOnClickListener { onClick(country) }
    }


    override fun getItemCount() = countries.size
}
