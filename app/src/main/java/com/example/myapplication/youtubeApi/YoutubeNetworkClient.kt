package com.example.myapplication.youtubeApi

import com.example.myapplication.MyApplication
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object YoutubeNetworkClient {

    private const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"

    private const val HTTP_HEADER_CACHE_CONTROL = "Cache-Control"
    private const val CACHE_MAX_AGE = "max-age=1800" //캐시 수명: 1800초(30분)

    private fun createOkHttpClient(): OkHttpClient {
        val context = MyApplication.appContext!!

        val cacheSize = 10L * 1024 * 1024 //캐시 크기: 10MB
        val cache = Cache(context.cacheDir, cacheSize)

        val interceptor = Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header(HTTP_HEADER_CACHE_CONTROL, CACHE_MAX_AGE)
                .build()

            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .build()
    }

    private val youtubeRetrofit = Retrofit.Builder()
        .baseUrl(YOUTUBE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            createOkHttpClient()
        )
        .build()
    val youtubeNetWork = youtubeRetrofit.create(YoutubeNetWorkInterface::class.java)
}