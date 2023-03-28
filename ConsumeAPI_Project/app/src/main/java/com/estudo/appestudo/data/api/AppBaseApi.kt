package com.estudo.appestudo.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class AppBaseApi {
    companion object {
        private const val BASE_URL = "http://172.17.0.1:3000"
        private val client: OkHttpClient
            get() = OkHttpClient
                    .Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                        request.addHeader("Content-Type", "application/json") //Can se add hir too authorization token JWT
                        chain.proceed(request.build())
                     }
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()

        private val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        fun retrofit(): WordApi = retrofit.create(WordApi::class.java)
    }
}
