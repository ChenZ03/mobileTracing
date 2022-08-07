package com.example.contacttracingproject.application

import android.app.Application
import com.example.contacttracingproject.repository.UserRepository
import com.example.contacttracingproject.roomDatabase.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UserApplication : Application() {
    val database : roomDatabase by lazy {roomDatabase.getDatabase(this)}
    val repository : UserRepository by lazy {UserRepository(database.userDao())}

}