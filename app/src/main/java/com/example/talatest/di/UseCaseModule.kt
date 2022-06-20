package com.example.talatest.di

import com.example.talatest.data.LoanRepository
import com.example.talatest.domain.loan.GetAllUserLoansUseCase
import com.example.talatest.domain.loan.GetFilterLoansByStatusUseCase
import com.example.talatest.domain.login.GetValidUserUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun getValidUserUseCase() =  GetValidUserUseCase()
    @Provides
    fun getAllUserLoansUseCase(repository: LoanRepository) = GetAllUserLoansUseCase(repository)
    @Provides
    fun getFilterLoansByStatusUseCase(repository: LoanRepository) = GetFilterLoansByStatusUseCase(repository)
}