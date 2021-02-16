package com.example.faceitstatchecker.Network

import com.example.faceitstatchecker.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by George Hart on 15/02/2021;
 */
object FSCRetrofit {
    val retrofitInstance: Retrofit
    init {
        val interceptor = Interceptor { interceptorChain ->
            val newRequest = interceptorChain.request().newBuilder().header("Authorization", "Bearer $${BuildConfig.FaceitApiKey}").build()
            interceptorChain.proceed(newRequest)
        }
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofitInstance = Retrofit.Builder().baseUrl("https://open.faceit.com/data/v4/").client(httpClient).build()
    }
}