package com.example.taller1icm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1icm.databinding.ActivityRandomGreetBinding
import kotlin.random.Random

class RandomGreetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomGreetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomGreetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //traer el string enviado por intent en MainActivity y guardarlo en idioma
        val idioma = intent.getStringExtra("seleccion")
        //aqui si puedo usar el !! porque por default viene seleccionado Español, entonces siempre llegará algo
        binding.tvGreeting.text = saludoAleatorio(idioma!!).toString()



        binding.btnVolver.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun saludoAleatorio(idioma:String): String {
        val saludo= when (idioma) {
            "Español" -> listOf("¡Hola!", "¡Buenos días!", "¡Qué tal!", "¡Saludos!")
            "English" -> listOf("Hello!", "Hi!", "Good day!", "Howdy!", "Greetings!")
            "Français" -> listOf("Bonjour!", "Salut!", "Coucou!", "Allô!", "Bienvenue!")
            "Italiano" -> listOf("Ciao!", "Buongiorno!", "Salve!", "Ehilà!", "Benvenuto!")
            "Deutsch" -> listOf("Hallo!", "Guten Tag!", "Servus!", "Moin!", "Grüß Gott!")
            else -> listOf("¡Hola Usuario!")
        }
        return saludo[Random.nextInt(saludo.size)]



    }

}