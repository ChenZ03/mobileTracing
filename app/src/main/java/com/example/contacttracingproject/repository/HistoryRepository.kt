package com.example.contacttracingproject.repository

import android.util.Log
import com.example.contacttracingproject.`interface`.HistoryRetrofit
import com.example.contacttracingproject.dao.HistoryDao
import com.example.contacttracingproject.data.HistoryRequest
import com.example.contacttracingproject.data.HistoryResponse
import com.example.contacttracingproject.models.History
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class HistoryRepository{
    suspend fun getHistory() : Response<HistoryResponse>? {
        return HistoryRetrofit.getApi()?.getHistory()
    }

    suspend fun insertHistory(historyRequest: HistoryRequest) : Response<HistoryResponse>? {
       Log.d("HistoryRepository", "insertHistory: ${historyRequest}")
        return HistoryRetrofit.getApi()?.insertHistory(historyRequest)
    }
}