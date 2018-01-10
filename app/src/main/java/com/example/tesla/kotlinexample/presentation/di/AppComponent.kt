package com.example.tesla.kotlinexample.presentation.di

import com.example.tesla.kotlinexample.presentation.presentation.App
import com.example.tesla.kotlinexample.presentation.screens.MainActivity
import dagger.Component

/**
 * Created by TESLA on 10.01.2018.
 */
@Component(dependencies = arrayOf(ModelComponent::class))
interface AppComponent {
    abstract fun inject(app: App)
    abstract fun inject(activity: MainActivity)
}