package com.example.tesla.kotlinexample.presentation.di

import com.example.tesla.kotlinexample.data.service.Api
import dagger.Component
import javax.inject.Singleton

/**
 * Created by TESLA on 10.01.2018.
 */
@Singleton
@Component(modules = arrayOf(
        NetworkModule::class,
        ApiModule::class,
        ContextModule::class))
interface ModelComponent {
    abstract fun exposeApi(): Api
}