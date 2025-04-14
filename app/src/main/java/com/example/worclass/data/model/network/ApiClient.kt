package com.example.worclass.data.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ES LA CONFIG PARA EL HTTP DE RETRO
object RetrofitClient {

    // URL API
    private const val BASE_URL = "https://fakeapi.rickbit.net/fakeapi/public/api/"



    val api: ApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // PASA JSON
            .build()
            .create(ApiService::class.java)
    }
}