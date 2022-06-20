package com.example.talatest.ui.viewmodel

import android.content.Context

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.talatest.data.LoanRepository
import com.example.talatest.di.servicelocator.IGetValidUserUseCase
import com.example.talatest.di.servicelocator.ServiceLocator
import com.example.talatest.di.servicelocator.ServiceLocator2
import com.example.talatest.domain.login.GetValidUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(){

    val isValidUser = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun validUser(context: Context?, userName: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = ServiceLocator.getService(IGetValidUserUseCase::class).validateUser(context,userName)
//            val result = getValidUserUseCase.validateUser(context,userName)
            isValidUser.postValue(result)
            isLoading.postValue(false)
        }
    }
}