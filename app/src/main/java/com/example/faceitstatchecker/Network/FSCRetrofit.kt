package com.example.faceitstatchecker.Network

import com.example.faceitstatchecker.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by George Hart on 15/02/2021;
 */
object FSCRetrofit {
    val retrofitInstance: Retrofit
    init {
        val authInterceptor = Interceptor { interceptorChain ->
            val newRequest = interceptorChain.request().newBuilder().header("Authorization", "Bearer ${BuildConfig.FaceitApiKey}").build()
            interceptorChain.proceed(newRequest)
        }
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).build()

        retrofitInstance = Retrofit.Builder().baseUrl("https://open.faceit.com/data/v4/").client(httpClient).addConverterFactory(GsonConverterFactory.create()).build()
    }
}