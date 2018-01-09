package com.example.tesla.kotlinexample.data.service

import okhttp3.Interceptor
import okhttp3.Response
import android.provider.SyncStateContract.Helpers.update
import okhttp3.Request
import java.security.NoSuchAlgorithmException


/**
 * Created by TESLA on 10.01.2018.
 */
public class MarvelInterceptor(val key: String, val secret: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val timeStamp = (System.currentTimeMillis() / 1000L).toString()
        val hash = getHash(timeStamp)

        val originalRequest = chain!!.request()
        val originalHttp = originalRequest!!.url()

        val url = originalHttp.newBuilder()
                .addQueryParameter("ts", timeStamp)
                .addQueryParameter("apikey", key)
                .addQueryParameter("hash", hash)
                .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

    private fun getHash(timeStamp: String): String {
        return md5(timeStamp + secret + key)
    }

    private fun md5(s: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest
                    .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0" + h
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

}