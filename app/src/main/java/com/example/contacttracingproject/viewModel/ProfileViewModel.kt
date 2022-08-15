package com.example.contacttracingproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.models.User
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileViewModel(val repository: UserRepository) : BaseViewModel() {
    fun getUser(icNumber1: String) {
        viewModelScope.launch {
            var user = repository.getUser(icNumber1)
            icNumber.value = user.icNumber
            username.value = user.username
            phone.value = user.phone
            getUser(icNumber1)
        }
    }

    private fun getUser(user: User): Job = viewModelScope.launch(Dispatchers.IO) {
        repository.getUser(icNumber.value.toString())
    }
}

class ProfileViewModelFactory(
    private  val repository: UserRepository): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}