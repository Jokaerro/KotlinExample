package com.example.tesla.kotlinexample.data.service

import java.io.Serializable

/**
 * Created by TESLA on 10.01.2018.
 */
interface Task<out R>: Serializable {
    @Throws(Throwable::class)
    abstract fun exec(): R

    abstract fun cancel()
}