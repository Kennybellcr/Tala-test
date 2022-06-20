package com.example.talatest.domain.loan

import android.content.Context
import com.example.talatest.data.LoanRepository
import com.example.talatest.data.model.LoanModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilterLoansByStatusUseCase @Inject constructor(
    private val repository: LoanRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO){

    suspend operator fun invoke(context: Context?,status: String,userName: String?):List<LoanModel> {
        lateinit var loans: List<LoanModel>
        withContext(dispatcher) {
            loans = repository.getFilterLoans(context,status,userName)
        }
        return loans.ifEmpty {
            emptyList()
        }

    }
}