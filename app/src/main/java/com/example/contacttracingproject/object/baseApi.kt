package com.example.contacttracingproject.`object`

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object baseApi {
    val baseUrl = "http://localhost:3000/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}