package com.example.tesla.kotlinexample.data.service

import android.os.Parcelable
import java.io.Serializable
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.HashMap

/**
 * Created by TESLA on 10.01.2018.
 */
class ServiceContainer() {
    val map = HashMap<Class<*>, Any>()

    companion object {
        @JvmStatic
        val instance by lazy {
            ServiceContainer()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <S : Any> registerService(s: S) {
        if (s.javaClass.interfaces.isEmpty()) {
            throw IllegalArgumentException("Service instance must implement an interface")
        }

        val clazz = s.javaClass.interfaces[0]

        val proxy = Proxy.newProxyInstance(clazz.classLoader, arrayOf(clazz),
                object : InvocationHandler {
                    @Throws(Throwable::class)
                    override fun invoke(proxy: Any, method: Method,
                                        args: Array<Any>): Any {

                        if (method.declaringClass == Any::class.java) {
                            return method.invoke(this, *args)
                        }
                        val actual = method.invoke(s, *args)
                        if (actual is Task<*>) {

                            method.parameterTypes
                                    .filter {
                                        !it.isPrimitive && !Serializable::class.java
                                                .isAssignableFrom(it) && !Parcelable::class.java
                                                .isAssignableFrom(it)
                                    }
                                    .forEach {
                                        throw IllegalStateException("Service " +
                                                "method parameters must be " +
                                                "serializable")
                                    }
                            return TaskSerializableAdapter(
                                    actual,
                                    clazz,
                                    method.name,
                                    method.parameterTypes,
                                    args
                            )
                        }
                        return actual
                    }
                }) as S

        map.put(clazz, proxy)
    }

    @Suppress("UNCHECKED_CAST")
    fun <I> getService(clazz: Class<I>): I {
        return map[clazz] as I
    }
}