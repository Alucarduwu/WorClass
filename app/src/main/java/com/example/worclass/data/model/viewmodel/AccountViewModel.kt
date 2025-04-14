package com.example.worclass.data.model.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.data.model.network.RetrofitClient

import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response


class AccountViewModel: ViewModel() {


    val api = RetrofitClient.api

    // REGRESA CUENTA

    fun getAccounts(onResult: (Response<List<AccountModel>>) -> Unit) {
        viewModelScope.launch {
            try {

                val response = api.getAccounts()
                Log.d("debug", response.toString())
                onResult(response)
            } catch (exception: Exception) {

                Log.d("debug", "API ERROR: $exception")
            }
        }
    }


    fun getAccount(id: Int, onResult: (Response<AccountModel>) -> Unit) {
        viewModelScope.launch {
            try {

                val response = api.getAccount(id)
                Log.d("debug", response.toString())
                onResult(response)
            } catch (exception: Exception) {

                Log.d("debug", "API ERROR: $exception")
            }
        }
    }

    // AQUI YA CREAMOS

    fun createAccount(service: AccountModel, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {

                val response = api.addAccount(service)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    Log.d("debug", "MSG: ${response.body()}")
                    onResult(jsonResponse)
                } else {
                    Log.d("debug", "ERROR: ${response.body()}")
                    onResult(null)
                }
            } catch (exception: Exception) {

                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }


    // YA AQUI ACTUALIZA
    fun updateAccount(id: Int, account: AccountModel, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {

                val response = api.updateAccount(id, account)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    Log.d("debug", "MSG: ${response.body()}")
                    onResult(jsonResponse)
                } else {
                    Log.d("debug", "ERROR: ${response.body()}")
                    onResult(null)
                }
            } catch (exception: Exception) {

                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)  // Llama al callback con valor nulo en caso de fallo.
            }
        }
    }

    // LA TAREA DEL ELIMINAR

    fun deleteAccount(id: Int, onResult: (JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {

                val response = api.deleteAccount(id)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    Log.d("debug", "MSG: ${response.body()}")
                    onResult(jsonResponse)
                } else {
                    Log.d("debug", "ERROR: ${response.body()}")
                    onResult(null)
                }
            } catch (exception: Exception) {

                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }
}