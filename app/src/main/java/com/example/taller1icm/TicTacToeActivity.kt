package com.example.taller1icm

import android.os.Bundle
import androidx.core.content.ContextCompat // para poder traer los colores en R de values
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import com.example.taller1icm.databinding.ActivityTicTacToeBinding

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTicTacToeBinding
    private var tablero = Array(3) { Array(3) { "" } }
    private var jugadorActual = "X"
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniciarTablero()
        binding.btnReset.setOnClickListener { reiniciarJuego() }
    }

    private fun iniciarTablero() {
        val buttons = arrayOf(
            binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6,
            binding.btn7, binding.btn8, binding.btn9
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener { clickButton(button, index) }
        }
    }

    private fun clickButton(button: Button, index: Int) {
        //para que no deje volver a seleccionar un boton si ya se ganó o ya esta seleccionada
        if (gameOver || button.text.isNotEmpty()) return

        //segun el index del boton, hallar en cual se hizo click
        val row = index / 3
        val col = index % 3

        //"escribir" en el boton clickeado el movimiento, X o O dependiendo del turno
        tablero[row][col] = jugadorActual
        button.text = jugadorActual

        // Cambiar el color en el que se convierte el boton clickeado dependiendo del jugador!!
        if (jugadorActual == "X") {
            val color = ContextCompat.getColor(this, R.color.green)

            button.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.strongBlue))
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        if (revisarGanador()) {
            //con el binding al botón del layout se modifica su contenido
            binding.tvPlayerOne.text = "Ganador: $jugadorActual"
            gameOver = true
        } else if (tableroLleno()) { //si con este turno se llenó el tablero y no se cumplió con condición ganador arriba, es empate
            binding.tvPlayerOne.text = "Empate"
            gameOver = true
        } else {
            //cambiar el turno DESPUES de revisar si ya se ganó no antes
            jugadorActual = if (jugadorActual == "X") "O" else "X"
        }
    }

    private fun revisarGanador(): Boolean {
        for (i in 0..2) {
            //si hay un mismo símbolo en las 3 filas de la columna i, ese jugador ganó. se revisa para todos los posibles i
            if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) return true
            if (tablero[0][i] == jugadorActual && tablero[1][i] == jugadorActual && tablero[2][i] == jugadorActual) return true
        }
        //se revisa si hay un mismo simbolo en alguno de los 2 diagonales posibles
        if (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) return true
        if (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual) return true

        return false
    }

    private fun tableroLleno(): Boolean {
        // si hay algo en todos los botones en tablero
        return tablero.all { row -> row.all { it.isNotEmpty() } }
    }

    private fun reiniciarJuego() {
        //reasigna las variables a las inciales, borra el texto de símbolos del tablero e inicia con jugador X
        tablero = Array(3) { Array(3) { "" } }
        jugadorActual = "X"
        gameOver = false
        binding.tvPlayerOne.text = "PLAYER ONE (X)"

        val buttons = arrayOf(
            binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6,
            binding.btn7, binding.btn8, binding.btn9
        )

        //toca volver a cambiar el color de los botones para que no queden con el color del juego anterior
        buttons.forEach {
            it.text = ""
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        }
    }
}
