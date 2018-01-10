package com.example.tesla.kotlinexample.data

import java.io.Serializable

/**
 * Created by TESLA on 10.01.2018.
 */
data class ErrorResponse(val code: String, val status: String) : Serializable