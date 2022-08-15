package com.example.contacttracingproject.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel(): ViewModel(),
    Observable {

    protected var viewModelJob = Job()
    protected var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // User
    @Bindable
    var username = MutableLiveData<String>()

    @Bindable
    var icNumber = MutableLiveData<String>()

    @Bindable
    var password = MutableLiveData<String>()

    @Bindable
    var password2 = MutableLiveData<String>()

    @Bindable
    var phone = MutableLiveData<String>()

    var _errorToast: MutableSharedFlow<String> = MutableSharedFlow()
    var errorToast: SharedFlow<String> = _errorToast

    var _finish = MutableLiveData<Boolean>()
    var finish: LiveData<Boolean> = _finish

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}