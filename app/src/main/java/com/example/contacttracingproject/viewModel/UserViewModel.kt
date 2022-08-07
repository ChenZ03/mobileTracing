package com.example.contacttracingproject.viewModel

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.contacttracingproject.models.User
import com.example.contacttracingproject.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    //get user data by id
    fun getUser(icNumber: String): LiveData<User> {
        return repository.getUser(icNumber).asLiveData()
    }

    fun insertUser(user: User) {
        repository.insert(user)
    }

    fun updateUser(user: User) {
        repository.update(user)
    }

    fun deleteUser(user: User) {
        repository.delete(user)
    }

    fun loginUser(icNumber: String, password: String): LiveData<User> {
        return repository.loginUser(icNumber, password).asLiveData()
    }

}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}