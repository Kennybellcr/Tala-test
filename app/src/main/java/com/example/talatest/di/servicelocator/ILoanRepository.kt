package com.example.talatest.di.servicelocator

import android.content.Context
import com.example.talatest.data.model.LoanModel

interface ILoanRepository {

    suspend fun getAllLoans(context: Context?):List<LoanModel>

    suspend fun validateUser(context: Context?, userName: String):Boolean

    suspend fun getFilterLoans(context: Context?, status: String, userName: String?):List<LoanModel>
}