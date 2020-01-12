package com.nurisis.seemyclothappp.di

import com.nurisis.seemyclothappp.data.NaverSearchApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { createOkHttp() }

    single {
        createWebService<NaverSearchApi>(get(), "https://openapi.naver.com")
    }
}

fun createOkHttp() : OkHttpClient {

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String) : T{
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        // For json parser
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}