package com.example.contacttracingproject.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.EditProfile
import com.example.contacttracingproject.models.User
import com.example.contacttracingproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditProfileViewModel(val repository: UserRepository) : BaseViewModel() {
    fun editProfile(icNumber1: String, newName: String, newPhone: String){
        viewModelScope.launch {
            val user = repository.getUser(icNumber1)
            if(user != null) {
                val updatedUser = User(user.id, user.icNumber, user.password, newName, 0, newPhone)
                updateUser(updatedUser)
//                _errorToast.emit("Edit Success")
                _finish.value = true
            }else{
                _errorToast.emit("An error has occured")
                _finish.value = false
            }
        }
    }

    private fun updateUser(user: User): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    private fun getUser(icNumber1: String): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(icNumber.value.toString())
        }
}

class EditProfileViewModelFactory(
    private  val repository: UserRepository): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}