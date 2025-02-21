package com.example.taller1icm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1icm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Llamado a actividad TicTacToe
        binding.ticTacToe.setOnClickListener {
            //no hace falta mandarle nada extra
            startActivity(Intent(baseContext, TicTacToeActivity::class.java))
        }


        //Llamado a actividad randomGreet
        binding.randomGreet.setOnClickListener {
            //crear la variable con el texto del lenguaje elegifo
            val seleccion = binding.spinner.selectedItem.toString()
            val intent = Intent(baseContext, RandomGreetActivity::class.java)
            //antes de iniciar el intent, agregar la selección como info. para pasar
            intent.putExtra("seleccion", seleccion)
            startActivity(intent)
        }

        binding.countries.setOnClickListener {
            startActivity(Intent(this, CountriesActivity::class.java))
        }


    }
}