package com.example.tesla.kotlinexample.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by TESLA on 10.01.2018.
 */
@Module
class ContextModule(private val context: Context) {
    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }
}