package com.example.worclass.data.model.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.data.model.network.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response

// Esta clase es un ViewModel que gestiona la lógica de negocio relacionada con las cuentas.
// Se encarga de realizar las llamadas a la API utilizando Retrofit y manejar la respuesta.
class AccountViewModel: ViewModel() {

    // Instancia de la API configurada previamente en RetrofitClient.
    val api = RetrofitClient.api

    // Función para obtener todas las cuentas.
    // Recibe un callback onResult para manejar la respuesta de la API.
    fun getAccounts(onResult: (Response<List<AccountModel>>) -> Unit) {
        viewModelScope.launch {
            try {
                // Realiza la llamada a la API para obtener las cuentas.
                val response = api.getAccounts()
                Log.d("debug", response.toString())  // Muestra la respuesta en log para depuración.
                onResult(response)  // Llama al callback con la respuesta obtenida.
            } catch (exception: Exception) {
                // Si ocurre un error, lo muestra en el log.
                Log.d("debug", "API ERROR: $exception")
            }
        }
    }

    // Función para obtener una cuenta específica por su ID.
    // Recibe un callback onResult para manejar la respuesta.
    fun getAccount(id: Int, onResult: (Response<AccountModel>) -> Unit) {
        viewModelScope.launch {
            try {
                // Realiza la llamada a la API para obtener una cuenta específica.
                val response = api.getAccount(id)
                Log.d("debug", response.toString())  // Muestra la respuesta en log para depuración.
                onResult(response)  // Llama al callback con la respuesta obtenida.
            } catch (exception: Exception) {
                // Si ocurre un error, lo muestra en el log.
                Log.d("debug", "API ERROR: $exception")
            }
        }
    }

    // Función para crear una nueva cuenta.
    // Recibe un objeto AccountModel con los datos de la cuenta y un callback onResult para manejar la respuesta.
    fun createAccount(service: AccountModel, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {
                // Realiza la llamada a la API para agregar una cuenta.
                val response = api.addAccount(service)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()  // Si la respuesta es exitosa, obtiene el cuerpo de la respuesta.
                    Log.d("debug", "MSG: ${response.body()}")  // Muestra la respuesta en log para depuración.
                    onResult(jsonResponse)  // Llama al callback con la respuesta obtenida.
                } else {
                    Log.d("debug", "ERROR: ${response.body()}")  // Si la respuesta es un error, lo muestra en log.
                    onResult(null)  // Llama al callback con valor nulo en caso de error.
                }
            } catch (exception: Exception) {
                // Si ocurre un error, lo muestra en el log.
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)  // Llama al callback con valor nulo en caso de fallo.
            }
        }
    }

    // Función para actualizar una cuenta existente.
    // Recibe el ID de la cuenta a actualizar, un objeto AccountModel con los nuevos datos y un callback onResult.
    fun updateAccount(id: Int, account: AccountModel, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {
                // Realiza la llamada a la API para actualizar la cuenta con el ID y los nuevos datos.
                val response = api.updateAccount(id, account)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()  // Si la respuesta es exitosa, obtiene el cuerpo de la respuesta.
                    Log.d("debug", "MSG: ${response.body()}")  // Muestra la respuesta en log para depuración.
                    onResult(jsonResponse)  // Llama al callback con la respuesta obtenida.
                } else {
                    Log.d("debug", "ERROR: ${response.body()}")  // Si la respuesta es un error, lo muestra en log.
                    onResult(null)  // Llama al callback con valor nulo en caso de error.
                }
            } catch (exception: Exception) {
                // Si ocurre un error, lo muestra en el log.
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)  // Llama al callback con valor nulo en caso de fallo.
            }
        }
    }

    // Función para eliminar una cuenta por su ID.
    // Recibe el ID de la cuenta a eliminar y un callback onResult para manejar la respuesta.
    fun deleteAccount(id: Int, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {
                // Realiza la llamada a la API para eliminar la cuenta con el ID especificado.
                val response = api.deleteAccount(id)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()  // Si la respuesta es exitosa, obtiene el cuerpo de la respuesta.
                    Log.d("debug", "MSG: ${response.body()}")  // Muestra la respuesta en log para depuración.
                    onResult(jsonResponse)  // Llama al callback con la respuesta obtenida.
                } else {
                    Log.d("debug", "ERROR: ${response.body()}")  // Si la respuesta es un error, lo muestra en log.
                    onResult(null)  // Llama al callback con valor nulo en caso de error.
                }
            } catch (exception: Exception) {
                // Si ocurre un error, lo muestra en el log.
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)  // Llama al callback con valor nulo en caso de fallo.
            }
        }
    }
}