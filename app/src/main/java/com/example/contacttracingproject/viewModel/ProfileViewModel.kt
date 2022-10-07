package com.example.contacttracingproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.data.LoginRequest
import com.example.contacttracingproject.data.UserResponse
import com.example.contacttracingproject.models.User
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    val userRepository = UserRepository()
    val result: MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()
    fun getUser(icNumber: String) {
        result.value = BaseResponse.Loading()
        viewModelScope.launch() {
            try {
                val response = userRepository.getUser(icNumber)
                if (response?.code() == 200) {
                    result.value = BaseResponse.Success(response.body())
                } else {
                    result.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                result.value = BaseResponse.Error(ex.message)
            }
            Log.d("profile idk", result.value.toString())
        }
    }

}
