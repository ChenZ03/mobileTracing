package com.example.contacttracingproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.data.UserData
import com.example.contacttracingproject.models.User
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(val repository: UserRepository) : BaseViewModel() {
    fun register(){
        if(password2.value != password.value) {
            viewModelScope.launch {
                _errorToast.emit("Password are not the same")
                _finish.value = false
            }
        }else{
            viewModelScope.launch {
                val user = repository.loginUser(icNumber.value.toString(), password.value.toString())
                if(user == null) {
                    signup(icNumber.value.toString(), password.value.toString())
                    _errorToast.emit("Register Success")
                    _finish.value = true
                }else{
                    _errorToast.emit("User already exists")
                    _finish.value = false
                }
            }
        }
    }

    private fun signup(icNumber: String, password: String): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.registerUser(icNumber, password)
        }
}

class RegisterViewModelFactory(
    private  val repository: UserRepository): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}