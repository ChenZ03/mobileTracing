package com.example.contacttracingproject.data

data class User(
    val _id: String,
    val username: String,
    val icNumber: String,
    val phone: String,
    val password: String,
    val age : Int,
)