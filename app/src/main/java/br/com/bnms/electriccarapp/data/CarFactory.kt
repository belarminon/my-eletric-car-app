package br.com.bnms.electriccarapp.data

import br.com.bnms.electriccarapp.domain.CarroDomain

object CarFactory {
    val list = listOf(
        CarroDomain(
            id = 1,
            preco = "R$ 300.000,00",
            bateria = "300 kWv",
            potencia = "300 CV",
            tempoRecarga = "23 min",
            urlPhoto = "www.google.com"
        ), CarroDomain(
            id = 2,
            preco = "R$ 300.000,00",
            bateria = "300 kWv",
            potencia = "300 CV",
            tempoRecarga = "23 min",
            urlPhoto = "www.google.com"
        ), CarroDomain(
            id = 3,
            preco = "R$ 300.000,00",
            bateria = "300 kWv",
            potencia = "300 CV",
            tempoRecarga = "23 min",
            urlPhoto = "www.google.com"
        )
    )
}