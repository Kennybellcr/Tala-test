package com.example.talatest.di.servicelocator

import com.example.talatest.data.LoanRepository
import com.example.talatest.domain.login.GetValidUserUseCase

class BuildServices() {
    init {
        ServiceLocator.addService(IGetValidUserUseCase::class,GetValidUserUseCase::class)
        ServiceLocator.addService(ILoanRepository::class,LoanRepository::class)
    }
}