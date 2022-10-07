package com.example.contacttracingproject.`interface`

import com.example.contacttracingproject.`object`.RetrofitHelper
import com.example.contacttracingproject.data.EditRequest
import com.example.contacttracingproject.data.LoginRequest
import retrofit2.http.*
import com.example.contacttracingproject.data.UserResponse
import retrofit2.Response

interface UserRetrofit {
    @GET("/profile/{ic}")
    suspend fun getUser(@Path("ic") ic: String): Response<UserResponse>

    @POST("/auth/register")
    suspend fun createUser(@Body loginRequest: LoginRequest): Response<UserResponse>

    @PUT("/profile/{ic}")
    suspend fun editProfile(@Path("ic") ic: String, @Body editRequest: EditRequest): Response<UserResponse>

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UserResponse>

    companion object{
        fun getApi() : UserRetrofit? {
            return RetrofitHelper.client?.create(UserRetrofit::class.java)
        }
    }

}