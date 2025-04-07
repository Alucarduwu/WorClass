package com.example.worclass.data.model.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worclass.data.model.model.UserModel
import com.example.worclass.data.model.network.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
class UserViewModel : ViewModel() {
    val api = RetrofitClient.api

    fun loginApi(user_model: UserModel, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {
                Log.d("debug", "Attempting login with user: ${user_model.username}")

                // Realizamos la llamada al API
                val response = api.login(user_model)

                // Verificamos si la respuesta fue exitosa
                if (response.isSuccessful) {
                    val jsonResponse = response.body()

                    // Validamos que el cuerpo de la respuesta no sea nulo
                    if (jsonResponse != null) {
                        Log.d("debug", "Login successful, response: $jsonResponse")
                        onResult(jsonResponse)
                    } else {
                        Log.d("debug", "Login failed, empty response body.")
                        onResult(null)
                    }
                } else {
                    // Aquí obtenemos más detalles del error
                    val errorBody = response.errorBody()?.string() ?: "No error body"
                    Log.d("debug", "ERROR: ${response.code()} - $errorBody")

                    // Si quieres ver más detalles, también puedes imprimir los encabezados de la respuesta
                    Log.d("debug", "Response headers: ${response.headers()}")

                    onResult(null)
                }
            } catch (exception: Exception) {
                // Manejo de excepción con más detalle
                Log.d("debug", "API CALL FAILED: Exception - ${exception.message}")
                onResult(null)
            }
        }
    }
}
