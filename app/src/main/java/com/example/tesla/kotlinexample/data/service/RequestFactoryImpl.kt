package com.example.tesla.kotlinexample.data.service

/**
 * Created by TESLA on 10.01.2018.
 */
class RequestFactoryImpl<I>(val context: ObservableController, val service: I): RequestFactory<I> {

    override fun <T> create(callFunc: RequestFactory.RequestFunction<Task<T>, I>): Request<T> {
        return RequestImpl(context, callFunc.call(service))
    }
}