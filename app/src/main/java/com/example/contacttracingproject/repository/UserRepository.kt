package com.example.contacttracingproject.repository


import com.example.contacttracingproject.`interface`.UserRetrofit
import com.example.contacttracingproject.data.UserData
import retrofit2.Response

class UserRepository(private val userRetrofit: UserRetrofit) {

    suspend fun getUser(icNumber : String): Response<UserData> {
        return userRetrofit.getUser(icNumber)
    }

    suspend fun registerUser(icNumber: String, password: String): Response<UserData> {
        return userRetrofit.createUser(icNumber, password)
    }

    suspend fun updateUser(user: UserData, id: String) = userRetrofit.updateUser(user, id)

    suspend fun loginUser(icNumber: String, password: String) : Response<UserData> {
        return userRetrofit.login(icNumber, password)
    }

}