package com.example.taller1icm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.countryName.text = countryName
        binding.capitalName.text = "Capital: $capital"
        binding.countryCode.text = "Código: $sigla"
    }
}
