package com.example.tesla.kotlinexample.presentation.presentation

/**
 * Created by TESLA on 10.01.2018.
 */
class AppConfig {
    companion object {
        fun getApiKey(): String {
            return AppConfig().apiKey
        }

        fun getSecret(): String {
            return AppConfig().secret
        }
    }

    val apiKey = "ff66bc92c276b24a2d5555838481715d"
    val secret = "30e0719ed87484a3082d6bfb2fe2476a5b53c7ae"
}