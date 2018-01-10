package com.example.tesla.kotlinexample.presentation.di

import android.content.Context
import com.example.tesla.kotlinexample.data.service.MarvelInterceptor
import com.example.tesla.kotlinexample.presentation.presentation.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by TESLA on 10.01.2018.
 */
@Module
class AuthModule {
    @Provides
    @Singleton
    @Named("authInterceptor")
    fun provideAuthInterceptor(): Interceptor {
        return MarvelInterceptor(AppConfig.getApiKey(), AppConfig.getSecret())
    }
}