package br.com.bnms.electriccarapp.domain

data class CarroDomain (
    val id: Int,
    val preco: String,
    val bateria: String,
    val potencia: String,
    val tempoRecarga: String,
    val urlPhoto: String,
    var isFavorite: Boolean
)