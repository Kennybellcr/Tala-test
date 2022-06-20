package com.example.talatest.di.servicelocator

import android.content.Context

interface IGetValidUserUseCase {
    suspend fun validateUser(context: Context?, userName: String):Boolean
}