package com.example.contacttracingproject.`interface`

import retrofit2.Response
import retrofit2.http.*
import com.example.contacttracingproject.data.UserData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface UserRetrofit {
    @GET("/user")
    suspend fun getUser(@Query("id") id: String): Response<UserData>

    @POST("/auth/register")
    suspend fun createUser(@Body icNumber: String, password: String): Response<UserData>

    @PUT("/user")
    suspend fun updateUser(@Body user: UserData, id: String): Response<UserData>

    @POST("/auth/login")
    suspend fun login(@Body icNumber: String, password: String): Response<UserData>

}