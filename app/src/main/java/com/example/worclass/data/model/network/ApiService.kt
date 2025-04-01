package com.example.worclass.data.model.network


import com.example.worclass.data.model.model.UserModel
import com.google.gson.JsonObject

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService{
    @POST("user")
    suspend fun login(@Body user: UserModel): Response<JsonObject>
}