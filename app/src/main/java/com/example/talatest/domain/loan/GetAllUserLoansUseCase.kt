package com.example.talatest.domain.loan

import android.content.Context
import com.example.talatest.data.LoanRepository
import com.example.talatest.data.model.LoanModel
import javax.inject.Inject

class GetAllUserLoansUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(context: Context?):List<LoanModel> {
        val loans = repository.getAllLoans(context)
        return if (loans.isNotEmpty()){
            loans
        }else{
            emptyList()
        }
    }
}