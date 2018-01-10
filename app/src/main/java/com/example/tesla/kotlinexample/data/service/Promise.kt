package com.example.tesla.kotlinexample.data.service

import java.io.Serializable

/**
 * Created by TESLA on 10.01.2018.
 */
public interface Promise<T> {
    interface SuccessAction<T> : Serializable {
        fun call(t: T)
    }

    interface ErrorAction : Serializable {
        fun call(e: Throwable)
    }

    interface Action : Serializable {
        fun call()
    }

    abstract fun cancel()

    abstract fun isCancelled(): Boolean

    abstract fun hasTerminated(): Boolean
}