package com.example.contacttracingproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.data.HistoryRequest
import com.example.contacttracingproject.data.HistoryResponse
import com.example.contacttracingproject.models.History
import com.example.contacttracingproject.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val historyRepo = HistoryRepository()
    val historyResult : MutableLiveData<BaseResponse<HistoryResponse>> = MutableLiveData()

    fun getHistory() {
        historyResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try{
                val response = historyRepo.getHistory()
                if(response?.code() == 200){
                    historyResult.value = BaseResponse.Success(response.body())
                }else{
                    historyResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception){
                historyResult.value = BaseResponse.Error(e.message)
            }
            Log.d("HistoryViewModel", "getHistory: ${historyResult.value}")
        }
    }

    fun insertHistory(ic : String, location: String, dateTime: String){
        historyResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try{
                val historyRequest = HistoryRequest(ic, location, dateTime)
                val response = historyRepo.insertHistory(historyRequest)
                if(response?.code() == 200){
                    historyResult.value = BaseResponse.Success(response.body())
                }else{
                    historyResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception){
                historyResult.value = BaseResponse.Error(e.message)
            }
            Log.d("HistoryViewModel", "insertHistory: ${historyResult.value}")
        }
    }

}
