package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class HistoryRequest (
    @SerializedName("icNumber")
    val icNumber: String,
    @SerializedName("location")
    val location : String,
    @SerializedName("dateTime")
    val dateTime : String
)