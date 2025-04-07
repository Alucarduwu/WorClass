package com.example.worclass.data.model.network


import com.example.worclass.data.model.model.AccountModel
import com.example.worclass.data.model.model.UserModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST ("user")
    suspend fun login(@Body user: UserModel): Response<JsonObject>

    @GET("service")
    suspend fun getAccounts(): Response<List<AccountModel>>

    @GET("service/(id)")
    suspend fun getAccount(@Path("id") id: Int): Response<AccountModel>

    @POST("service")
    suspend fun adAccount(@Body service:AccountModel): Response<JsonObject>
}