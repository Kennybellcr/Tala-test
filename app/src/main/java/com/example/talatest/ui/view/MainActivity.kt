package com.example.talatest.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.talatest.R
import com.example.talatest.data.LoanRepository
import com.example.talatest.di.servicelocator.ILoanRepository
import com.example.talatest.di.servicelocator.ServiceLocator
import com.example.talatest.di.servicelocator.ServiceLocator2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ServiceLocator()
        ServiceLocator2
    }
}