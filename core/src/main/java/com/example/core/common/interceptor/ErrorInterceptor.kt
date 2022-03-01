package com.example.core.common.interceptor

import com.example.core.common.error.ApiException
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            throw when (response.code) {

                in 500..599 -> ApiException.IntervalServerError
                in 400..499 -> {
                    //here I'll parse the error into error json and throw the message in ApiError
                    val responseBody = response.body?.string()
                    responseBody?.let {
                        // parse error string into json body here
                    }
                    ApiException.ApiError("Error from server")
                }

                else -> ApiException.UnknownError
            }
        }

        return response
    }
}