package com.example.worclass.data.model.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Este objeto proporciona la configuración para realizar peticiones HTTP utilizando Retrofit.
// Se asegura de que solo haya una instancia de Retrofit (patrón Singleton).
object RetrofitClient {

    // URL base de la API a la que se hará la solicitud.
    private const val BASE_URL = "https://fakeapi.rickbit.com/api/"
    //https://fakeapi.rickbit.net/fakeapi/public/api/

    // Instancia de la API que se utilizará para realizar las peticiones.
    // La instancia se crea solo cuando se necesita gracias a 'lazy' para optimizar el rendimiento.
    val api: ApiService by lazy {
        // Configuración de Retrofit para hacer solicitudes HTTP.
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Establece la URL base de las solicitudes.
            .addConverterFactory(GsonConverterFactory.create())  // Añade un convertidor para convertir las respuestas JSON en objetos Kotlin.
            .build()  // Construye la instancia de Retrofit.
            .create(ApiService::class.java)  // Crea la implementación de la interfaz ApiService.
    }


}