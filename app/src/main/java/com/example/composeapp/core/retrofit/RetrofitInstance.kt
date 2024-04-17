package com.example.composeapp.core.retrofit

import com.example.composeapp.features.home.data.api.HomeApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(
                Interceptor { chain ->
                    val request = chain.request()
                    val response =
                        request.newBuilder().header("content-type", "application/json").build()
                    return@Interceptor chain.proceed(response)
                },
            ).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttp)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val homeApi: HomeApi by lazy {
        retrofit.create(HomeApi::class.java)
    }
}