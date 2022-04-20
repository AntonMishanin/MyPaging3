package com.my.mypaging3.auth.github

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 120_000L
private const val AUTH_URL = "https://github.com"
const val CONTENT_URL = "https://api.github.com"
private const val EMAIL_SCOPE = "user:email"

class RemoteFactory(private val token: String = "") {
    fun provideRetrofit(baseUrl: String = AUTH_URL): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(provideRxAdapterFactory())
        .addConverterFactory(provideGsonConverterFactory())
        .build()

    private fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    private fun provideRxAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLogging())
            .addInterceptor(provideInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun provideLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun provideInterceptor(): Interceptor = CustomInterceptor(token)
}

class CustomInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            //.addQueryParameter("client_id", KeyWrapper().provideClientId())
            //.addQueryParameter("scope", EMAIL_SCOPE)
            .build()
        val request = chain.request()
            .newBuilder()
           // .addHeader("Authorization", token)
            .url(url)
            .build()
        return chain.proceed(request)
    }
}