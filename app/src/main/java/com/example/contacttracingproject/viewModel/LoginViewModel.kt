package com.example.contacttracingproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.data.LoginRequest
import com.example.contacttracingproject.data.UserResponse
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()

    fun loginUser(ic: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    icNumber = ic
                    , password = pwd
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
            Log.d("idk", loginResult.value.toString())
        }
    }
}