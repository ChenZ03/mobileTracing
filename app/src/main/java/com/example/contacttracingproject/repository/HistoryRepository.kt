package com.example.contacttracingproject.repository

import com.example.contacttracingproject.dao.HistoryDao
import com.example.contacttracingproject.models.History
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {
    val histories: Flow<List<History>> = historyDao.getAll()

    suspend fun insert(history: History) {
        historyDao.insert(history)
    }
}