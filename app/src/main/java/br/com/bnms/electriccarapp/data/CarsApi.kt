package br.com.bnms.electriccarapp.data

import br.com.bnms.electriccarapp.domain.CarroDomain
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {

    @GET("my-electric-car-app.json")
    fun getAllCars(): Call<List<CarroDomain>>
}