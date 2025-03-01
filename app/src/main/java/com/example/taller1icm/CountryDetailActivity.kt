package com.example.taller1icm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.taller1icm.databinding.ActivityCountryDetailBinding

class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryName = intent.getStringExtra("country_name") ?: "Desconocido"
        val capital = intent.getStringExtra("capital") ?: "Desconocida"
        val sigla = intent.getStringExtra("sigla") ?: "N/A"
        val flagUrl = "https://flagcdn.com/w320/${sigla.lowercase()}.png"

        binding.countryName.text = countryName
        binding.capitalName.text = "Capital: $capital"
        binding.countryCode.text = "Código: $sigla"

        Glide.with(this)
            .load(flagUrl)
            .into(binding.countryFlag)
    }
}
