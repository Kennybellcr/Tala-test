package com.example.talatest.domain.login

import android.content.Context
import com.example.talatest.data.LoanRepository
import com.example.talatest.di.servicelocator.IGetValidUserUseCase
import com.example.talatest.di.servicelocator.ILoanRepository
import com.example.talatest.di.servicelocator.ServiceLocator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetValidUserUseCase constructor(private val dispatcher: CoroutineDispatcher = Dispatchers.IO): IGetValidUserUseCase {

    override suspend fun validateUser(context: Context?, userName: String):Boolean {
        var result: Boolean
        withContext(dispatcher){
            Thread.sleep(1000)
//            result = repository.validateUser(context,userName)
            result = ServiceLocator.getService(ILoanRepository::class).validateUser(context,userName)
        }
        return result
    }
}