package com.example.contacttracingproject.viewModel

import androidx.lifecycle.*
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : BaseViewModel() {

    fun login(){
        if(icNumber.value.isNullOrEmpty() || password.value.isNullOrEmpty()){
            viewModelScope.launch {
                _errorToast.emit("Login unsuccessful. Please retry.")
                _finish.value = false
            }
        }else{
            viewModelScope.launch {
                val user = repository.loginUser(icNumber.value.toString(), password.value.toString())
                if(user != null){
//                    user.value = user
                    _finish.value = true
                }else{
                    _errorToast.emit("Login unsuccessful. Please retry.")
                    _finish.value = false
                }
            }
        }
    }
}

class LoginViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}