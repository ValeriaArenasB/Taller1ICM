package com.example.taller1icm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
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
            // Leer el archivo JSON desde 'res/raw/'
            val inputStream = resources.openRawResource(R.raw.paises)
            val jsonString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }

            val jsonObject = JSONObject(jsonString)

            // Verificar si el JSON tiene la clave "paises"
            if (jsonObject.has("paises")) {
                val jsonArray = jsonObject.getJSONArray("paises")

                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)

                    // Verificar si las claves existen antes de obtener sus valores
                    val nombrePais = item.optString("nombre_pais", "Desconocido")
                    val capital = item.optString("capital", "Sin capital")
                    val sigla = item.optString("sigla", "N/A")

                    val country = Country(nombrePais, capital, sigla)
                    countryList.add(country)
                }
            } else {
                Log.e("CountriesActivity", "JSON no contiene la clave 'paises'")
            }
        } catch (e: IOException) {
            Log.e("CountriesActivity", "Error al leer el JSON: ${e.message}")
        } catch (e: Exception) {
            Log.e("CountriesActivity", "Error procesando el JSON: ${e.message}")
        }
        return countryList
    }

}
