package com.example.talatest.di.app

import com.example.talatest.data.LoanRepository
import com.example.talatest.di.UseCaseModule
import com.example.talatest.di.viewModel.ViewModelFactoryModule
import com.example.talatest.domain.loan.GetAllUserLoansUseCase
import com.example.talatest.domain.loan.GetFilterLoansByStatusUseCase
import com.example.talatest.domain.login.GetValidUserUseCase
import com.example.talatest.ui.view.fragment.DashboardFragment
import com.example.talatest.ui.view.fragment.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ViewModelFactoryModule::class, UseCaseModule::class])
interface AppComponent {

    fun repository(): LoanRepository
    fun getValidUserUseCase(): GetValidUserUseCase
    fun getAllUserLoansUseCase(): GetAllUserLoansUseCase
    fun getFilterLoansByStatusUseCase(): GetFilterLoansByStatusUseCase

    fun inject(loginFragment: LoginFragment)
    fun inject(dashboardFragment: DashboardFragment)

}