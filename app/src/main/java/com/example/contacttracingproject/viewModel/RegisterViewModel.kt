package com.example.contacttracingproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.data.LoginRequest
import com.example.contacttracingproject.data.UserResponse
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo = UserRepository()
    val registerResult : MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()

    fun register(ic :String, pwd: String){
        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    icNumber = ic
                    , password = pwd
                )
                val response = userRepo.registerUser(loginRequest)
                if (response?.code() == 200) {
                    registerResult.value = BaseResponse.Success(response.body())
                } else {
                    registerResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
            Log.d("register idk", registerResult.value.toString())
        }
    }
}
