package com.example.talatest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModels: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModels[modelClass]
        val viewModel = provider?.get() ?: throw RuntimeException("Unsupported viewModel type $modelClass")
        return viewModel as T
    }
}