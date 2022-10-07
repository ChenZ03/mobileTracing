package com.example.contacttracingproject.repository


import android.util.Log
import com.example.contacttracingproject.`interface`.UserRetrofit
import com.example.contacttracingproject.data.EditRequest
import com.example.contacttracingproject.data.LoginRequest
import com.example.contacttracingproject.data.UserResponse
import retrofit2.Response

class UserRepository {

    suspend fun getUser(icNumber : String): Response<UserResponse>? {
        Log.d("user", icNumber)
        return UserRetrofit.getApi()?.getUser(icNumber)
    }
//
    suspend fun registerUser(loginRequest: LoginRequest): Response<UserResponse>? {
        Log.d("register", loginRequest.toString())
        return UserRetrofit.getApi()?.createUser(loginRequest)
    }
//
    suspend fun editProfile(icNumber: String, editRequest: EditRequest): Response<UserResponse>? {
        Log.d("edit", editRequest.toString())
        return UserRetrofit.getApi()?.editProfile(icNumber, editRequest)
    }

    suspend fun loginUser(loginRequest: LoginRequest) : Response<UserResponse>? {
        Log.d("req" , loginRequest.toString())
        return UserRetrofit.getApi()?.login(loginRequest = loginRequest)
    }

}