package com.example.tesla.kotlinexample.data.service

import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Created by TESLA on 10.01.2018.
 */
class TaskSerializableAdapter<out T>
        (val actual: Task<T>,
         val service: Class<*>,
         val method: String,
         val parameterTypes: Array<Class<*>>,
         val args: Array<Any>):
        Task<T> {

    @Transient private var actualTask: Task<T>? = null
    private var serviceTask: Class<*>? = null
    private var methodTask: String? = null
    private var parameterTypesTask: Array<Class<*>>? = null
    private var argsTask: Array<Any>? = null

    init {
        actualTask = actual
    }

    override fun exec(): T {
        return actualTask!!.exec()
    }

    override fun cancel() {
        actualTask!!.cancel()
    }

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.writeObject(service)
        out.writeObject(method)
        out.writeObject(parameterTypes)
        out.writeObject(args)
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(Throwable::class)
    private fun readObject(inputObject: ObjectInputStream) {
        serviceTask = inputObject.readObject() as Class<*>
        methodTask = inputObject.readObject() as String
        parameterTypesTask = inputObject.readObject() as Array<Class<*>>
        argsTask = inputObject.readObject() as Array<Any>

        val method = service.getDeclaredMethod(this.method,
                *this.parameterTypes)
        val obj = ServiceContainer.instance.getService(this.service)
        actualTask = method.invoke(obj, *this.args) as Task<T>
    }
}