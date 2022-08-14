package com.example.contacttracingproject.application

import android.app.Application
import com.example.contacttracingproject.roomDatabase.userDatabase
import com.example.contacttracingproject.repository.UserRepository
//import com.example.contacttracingproject.history.HistoryDatabase
//import com.example.contacttracingproject.history.HistoryRepository

class BaseApplication: Application() {
    val database: userDatabase by lazy {userDatabase.getInstance(this)}
    val repository: UserRepository by lazy {UserRepository(database.userDao())}
//    val historyDatabase: HistoryDatabase by lazy { HistoryDatabase.getDatabase(this)}
//    val historyRepository: HistoryRepository by lazy { HistoryRepository(historyDatabase.historyDao()) }
}