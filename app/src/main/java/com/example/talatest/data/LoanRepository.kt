package com.example.talatest.data

import android.content.Context
import com.example.talatest.data.model.LoanModel
import com.example.talatest.di.servicelocator.ILoanRepository
import com.example.talatest.domain.Utils
import java.util.*

class LoanRepository: ILoanRepository{

    override suspend fun getAllLoans(context: Context?):List<LoanModel>{
        return Utils.readJsonFile(context,"testData.json")
    }

    override suspend fun validateUser(context: Context?, userName: String):Boolean{
        val loans = Utils.readJsonFile(context,"testData.json")
        loans.map { it.username}
        val result = loans.find { userName.replace("\\s".toRegex(), "").lowercase().equals(it.username.toString().replace("\\s".toRegex(), "").lowercase()) }
        return result != null
    }

    override suspend fun getFilterLoans(context: Context?, status: String, userName: String?):List<LoanModel>{
        var loans = Utils.readJsonFile(context,"testData.json")
        val loansFilter: MutableList<LoanModel> = mutableListOf()
        var cont = 0
        loans.forEach {
            if (it.username?.replace("\\s".toRegex(), "")?.lowercase(Locale.getDefault()).equals(userName?.replace("\\s".toRegex(), "")?.lowercase()) &&
                it.loan?.status?.replace("\\s".toRegex(), "")?.lowercase(Locale.getDefault()).equals(status.replace("\\s".toRegex(), "").lowercase()) ){
                loansFilter.add(it)
            }
            cont++
        }
        return loansFilter
    }

}