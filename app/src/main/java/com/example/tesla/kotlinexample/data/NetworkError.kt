package com.example.tesla.kotlinexample.data

import java.io.IOException

/**
 * Created by TESLA on 10.01.2018.
 */
class NetworkError(val code: Int, override val message: String, val errorResponse: ErrorResponse) : IOException(code.toString() + " " + message) {
    override fun toString(): String {
        return "NetworkError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}'
    }
}