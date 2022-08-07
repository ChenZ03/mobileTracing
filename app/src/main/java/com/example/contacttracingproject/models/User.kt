package com.example.contacttracingproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "user_table")
class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "icNumber")
    var icNumber: String = ""

    @ColumnInfo(name = "name")
    var username: String = ""

    @ColumnInfo(name = "age")
    var age: Int = 0

    @ColumnInfo(name = "phone")
    var phone: String = ""

    @ColumnInfo(name="password")
    var password: String = ""

}