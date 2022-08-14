package com.example.contacttracingproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.contacttracingproject.models.History
import com.example.contacttracingproject.repository.HistoryRepository

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {
    val histories: LiveData<List<History>> = repository.histories.asLiveData()

    suspend fun insert(history: History) {
        repository.insert(history)
    }
}

class HistoryViewModelFactory(private val repository: HistoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(repository) as T
    }
}