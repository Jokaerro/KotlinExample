package com.example.tesla.kotlinexample.data.service

import com.example.tesla.kotlinexample.data.ErrorResponse
import com.example.tesla.kotlinexample.data.NetworkError
import com.example.tesla.kotlinexample.data.model.CharacterDataWrapper
import com.google.gson.Gson
import retrofit2.Call

/**
 * Created by TESLA on 10.01.2018.
 */
public class ApiImpl(val network: Network): Api {

    @Throws(Throwable::class)
    private fun <T> executeSync(call: Call<T>): T? {
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body()
        } else {
            val json = response.errorBody()!!.string()
            val errorResponse = Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)

            throw NetworkError(response.code(), response.message(), errorResponse)
        }
    }

    override fun getCharacters(): Task<CharacterDataWrapper?> {
        return object : Task<CharacterDataWrapper?> {
            @Throws(Throwable::class)
            override fun exec(): CharacterDataWrapper? {
                return executeSync(network.getCharacters())
            }

            override fun cancel() {

            }
        }
    }
}