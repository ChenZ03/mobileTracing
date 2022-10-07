package com.example.contacttracingproject.`interface`

import com.example.contacttracingproject.`object`.RetrofitHelper
import com.example.contacttracingproject.data.HistoryRequest
import com.example.contacttracingproject.data.HistoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HistoryRetrofit {
    @GET("/history")
    suspend fun getHistory(): Response<HistoryResponse>

    @POST("/history/insert")
    suspend fun insertHistory(@Body historyRequest: HistoryRequest): Response<HistoryResponse>

    companion object{
        fun getApi() : HistoryRetrofit? {
            return RetrofitHelper.client?.create(HistoryRetrofit::class.java)
        }
    }

}