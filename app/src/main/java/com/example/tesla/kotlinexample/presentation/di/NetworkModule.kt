package com.example.tesla.kotlinexample.presentation.di

import com.example.tesla.kotlinexample.data.service.Network
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by TESLA on 10.01.2018.
 */
@Module(includes = arrayOf(AuthModule::class))
class NetworkModule {
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://gateway.marvel.com/v1/public/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }

        val cacheDir = File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString())
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(cacheDir, cacheSize.toLong())

        return builder
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun provideInterceptors(@Named("tokenInterceptor") tokenInterceptor: Interceptor): List<Interceptor> {
        val interceptors = ArrayList<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        interceptors.add(loggingInterceptor)
        interceptors.add(tokenInterceptor)

        return interceptors
    }

    @Provides
    @Singleton
    fun provideXfitApi(retrofit: Retrofit): Network {
        return retrofit.create<Network>(Network::class.java)
    }
}