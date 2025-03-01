package com.example.taller1icm

data class Country(
    val nombrePais: String,
    val capital: String,
    val sigla: String
) {
    val flagUrl: String
        get() = "https://flagcdn.com/w320/${sigla.lowercase()}.png"
}

