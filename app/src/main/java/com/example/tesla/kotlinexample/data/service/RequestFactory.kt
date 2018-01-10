package com.example.tesla.kotlinexample.data.service

/**
 * Created by TESLA on 10.01.2018.
 */
interface RequestFactory<S> {
    abstract fun <T> create(callFunc: RequestFunction<Task<T>, S>): Request<T>

    interface RequestFunction<T, S> {
        fun call(s: S): T
    }
}