package com.example.taller1icm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller1icm.databinding.ActivityCountriesBinding
import org.json.JSONObject
import java.io.IOException

class CountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountriesBinding
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val countryList = loadCountriesFromJson()
        adapter = CountryAdapter(countryList) { country ->
            val intent = Intent(this, CountryDetailActivity::class.java).apply {
                putExtra("country_name", country.nombrePais)
                putExtra("capital", country.capital)
                putExtra("sigla", country.sigla)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun loadCountriesFromJson(): List<Country> {
        val countryList = mutableListOf<Country>()
        try {
            val jsonString = assets.open("paises.json").bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("paises")

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val country = Country(
                    item.getString("nombre_pais"),
                    item.getString("capital"),
                    item.getString("sigla")
                )
                countryList.add(country)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return countryList
    }
}
