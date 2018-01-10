package com.example.tesla.kotlinexample.data.service

/**
 * Created by TESLA on 10.01.2018.
 */
public abstract class Request<T> {

    companion object {
        @JvmStatic
        val instance by lazy {
            ServiceContainer()
        }
    }

    abstract fun onError(errorAction: Promise.ErrorAction): Request<T>

    abstract fun onCancelled(cancelledAction: Promise.Action): Request<T>

    abstract fun onFinally(finallyAction: Promise.Action): Request<T>

    abstract fun execute(successAction: Promise.SuccessAction<T>): Request<T>

    abstract fun execute()

    private var defaultErrorAction: Promise.ErrorAction = object : Promise.ErrorAction {
        override fun call(e: Throwable) {
            e.printStackTrace()
        }
    }

    fun setDefaultErrorAction(action: Promise.ErrorAction) {
        defaultErrorAction = action
    }

    fun <S : Any> registerService(s: S) {
        ServiceContainer.instance.registerService(s)
    }

    fun <S> with(controller: ObservableController,
                 service: Class<S>): RequestFactory<S> {
        return RequestFactoryImpl(controller, service)
    }
}