package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class EditRequest (
    @SerializedName("newName")
    val username: String,
    @SerializedName("newPhone")
    val phone: String,
)
