package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("icNumber")
    val icNumber: String,
    @SerializedName("password")
    val password: String
)
