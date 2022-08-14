package com.example.contacttracingproject.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @NonNull
    @ColumnInfo(name="icNumber")
    val icNumber: String,

    @NonNull
    @ColumnInfo(name= "location")
    val location: String,

    @NonNull
    @ColumnInfo(name = "dateTime")
    val dateTime: String,

)