package com.example.talatest.di.servicelocator

import com.example.talatest.domain.login.GetValidUserUseCase
import java.net.UnknownServiceException

object ServiceLocator3
{
    inline fun <reified T> getService(): T {
        val service = lookupService<T>()
        if (service != null) {
            return service
        }
        throw UnknownServiceException()
    }

    inline fun <reified T> lookupService(): T? {
            if (T::class == IGetValidUserUseCase::class) {
            return GetValidUserUseCase() as T
        }
        return null
    }
}