package com.example.tesla.kotlinexample.presentation.di

import com.example.tesla.kotlinexample.data.service.Api
import com.example.tesla.kotlinexample.data.service.ApiImpl
import com.example.tesla.kotlinexample.data.service.Network
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by TESLA on 10.01.2018.
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(network: Network): Api {
        return ApiImpl(network)
    }
}