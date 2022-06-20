package com.example.talatest.di.app

import android.app.Application
import com.example.talatest.data.LoanRepository
import com.example.talatest.domain.loan.GetAllUserLoansUseCase
import com.example.talatest.domain.loan.GetFilterLoansByStatusUseCase
import com.example.talatest.domain.login.GetValidUserUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {
    @Provides
    @Singleton
    fun provideRepository() =  LoanRepository()
    @Provides
    fun provideApplication(): Application = provideApplication()
}