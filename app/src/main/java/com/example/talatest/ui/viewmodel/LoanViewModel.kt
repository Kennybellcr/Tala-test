package com.example.talatest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.talatest.data.model.LoanModel
import com.example.talatest.domain.loan.GetFilterLoansByStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoanViewModel @Inject constructor(
    private val getFilterLoansByStatusUseCase: GetFilterLoansByStatusUseCase
) : ViewModel() {

    val loansList = MutableLiveData<List<LoanModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun filterLoans(context: Context?, filter: String, userName: String?) {
        viewModelScope.launch {
            isLoading.postValue(true)
                val result = getFilterLoansByStatusUseCase(context, filter, userName)
                if (!result.isNullOrEmpty()) {
                    loansList.postValue(result)
                }
                isLoading.postValue(false)

        }
    }
}