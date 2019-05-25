package com.arp.mvvmbaseandroid.common

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * This class is a OkHttp Interceptor to inject the auth token in every network request
 */

class SecurityInterceptor(private val userPref: SharedPreferences) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Accept", "application/json")
        //        if(userPref.isUserAvailable()){
        //            requestBuilder.addHeader("Authorization","Bearer " + userPref.getUserToken());
        //        }
        return chain.proceed(requestBuilder.build())
    }
}