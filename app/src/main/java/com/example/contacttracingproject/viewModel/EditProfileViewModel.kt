package com.example.contacttracingproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contacttracingproject.data.BaseResponse
import com.example.contacttracingproject.data.EditRequest
import com.example.contacttracingproject.data.UserResponse
import com.example.contacttracingproject.models.User
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.launch

class EditProfileViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo = UserRepository()
    val editResult : MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()

    fun editProfile(ic: String, username : String, phone : String){
        editResult.value = BaseResponse.Loading()

        viewModelScope.launch {
            try{
                val editRequest = EditRequest(username, phone)
                val response = userRepo.editProfile(ic, editRequest)
                if (response?.code() == 200){
                    editResult.value = BaseResponse.Success(response.body())
                }else{
                    editResult.value = BaseResponse.Error(response?.message())
                }
            }
            catch (e : Exception){
                editResult.value = BaseResponse.Error(e.message)
            }
            Log.d("EditProfileViewModel", "editProfile: ${editResult.value}")
        }
    }
}