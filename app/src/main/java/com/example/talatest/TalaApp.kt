package com.example.talatest

import android.app.Application
import com.example.talatest.di.app.AppComponent
import com.example.talatest.di.app.AppModule
import com.example.talatest.di.app.DaggerAppComponent

class TalaApp : Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}