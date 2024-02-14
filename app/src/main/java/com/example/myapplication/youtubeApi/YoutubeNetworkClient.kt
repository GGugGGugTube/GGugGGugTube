package com.example.myapplication.youtubeApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object YoutubeNetworkClient{

    private const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"


    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val dustRetrofit = Retrofit.Builder()
        .baseUrl(YOUTUBE_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
            createOkHttpClient()
        ).build()

    val youtubeNetWork: YoutubeNetWorkInterface = dustRetrofit.create(YoutubeNetWorkInterface::class.java)
}