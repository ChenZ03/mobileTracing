package com.example.contacttracingproject.application

import android.app.Application
import com.example.contacttracingproject.`interface`.UserRetrofit
import com.example.contacttracingproject.repository.HistoryRepository
import com.example.contacttracingproject.roomDatabase.userDatabase
import com.example.contacttracingproject.repository.UserRepository
import com.example.contacttracingproject.roomDatabase.HistoryDatabase

class BaseApplication: Application() {
    val historyDatabase: HistoryDatabase by lazy { HistoryDatabase.getInstance(this)}
//    val historyRepository: HistoryRepository by lazy { HistoryRepository(historyDatabase.historyDao()) }
}

