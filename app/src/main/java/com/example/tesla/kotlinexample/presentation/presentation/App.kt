package com.example.tesla.kotlinexample.presentation.presentation

import android.app.Application
import android.content.Context
import com.example.tesla.kotlinexample.data.service.Api
import com.example.tesla.kotlinexample.data.service.Request
import com.example.tesla.kotlinexample.presentation.di.AppComponent
import com.example.tesla.kotlinexample.presentation.di.ContextModule
import javax.inject.Inject

/**
 * Created by TESLA on 10.01.2018.
 */
public class App: Application() {

    companion object {
        @JvmStatic lateinit var context: App
    }

    private var injector: AppComponent? = null
//    private var api: Api

    @Inject
    internal lateinit var api: Api

    override fun onCreate() {
        super.onCreate()

        context = this

        injector = DaggerAppComponent.builder()
                .modelComponent(DaggerModelComponent.builder()
                        .contextModule(ContextModule(this))
                        .build())
                .build()

        injector!!.inject(this)

        Request.instance.registerService(api)
    }
}