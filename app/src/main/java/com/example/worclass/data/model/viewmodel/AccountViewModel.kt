package com.example.worclass.data.model.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.data.model.network.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response

class AccountViewModel : ViewModel() {
    private val api = RetrofitClient.api
    private val TAG = "AccountViewModel"

    fun getAccounts(onResult: (Response<List<AccountModel>>?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.getAccounts()
                Log.d(TAG, "Response: $response")

                if (response.isSuccessful) {
                    onResult(response)
                } else {
                    Log.d(TAG, "API ERROR: ${response.errorBody()?.string()}")
                    onResult(null)
                }

            } catch (exception: Exception) {
                Log.d(TAG, "API ERROR: $exception")
                onResult(null)
            }
        }
    }



    fun getAccount(id: Int, onResult: (Response<AccountModel>) -> Unit) {

        viewModelScope.launch {
            try {
                val response = api.getAccount(id)
                Log.d(TAG, "Response: $response")

                if (response.isSuccessful) {
                    onResult(response)
                } else {
                    Log.d(TAG, "API ERROR: ${response.errorBody()?.string()}")

                }

            } catch (exception: Exception) {
                Log.d(TAG, "API ERROR: $exception")

            }
        }
    }


    fun createAccount(service: AccountModel, onResult:(JsonObject?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.adAccount(service)
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    Log.d("debug", "${response.body()}")
                    onResult(jsonResponse)
                } else {
                    Log.d("debug", "Error: ${response.body()}")
                    onResult(null)
                }
            } catch (exception: Exception) {
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }

    }
}