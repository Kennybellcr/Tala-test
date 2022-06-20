package com.example.talatest.di.viewModel

import androidx.lifecycle.ViewModel
import com.example.talatest.ui.viewmodel.LoanViewModel
import com.example.talatest.ui.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoanViewModel::class)
    internal abstract fun loanViewModel(loanViewModel: LoanViewModel): ViewModel
}