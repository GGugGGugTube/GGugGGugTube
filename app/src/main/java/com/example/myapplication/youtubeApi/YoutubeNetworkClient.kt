package com.example.myapplication.youtubeApi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.myapplication.MyApplication
import com.example.myapplication.NetworkUtils
import com.google.api.client.extensions.android.AndroidUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object YoutubeNetworkClient {

    private const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
    private const val CACHE_CONTROL = "Cache-Control"
    private fun createOkHttpClient(): OkHttpClient {
        val context = MyApplication.appContext!!

        val cacheSize = 10L * 1024 * 1024 //캐시 크기: 10MB
        val cache = Cache(context.cacheDir, cacheSize)

        val cacheInterceptor = Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(30, TimeUnit.MINUTES)
                .build()

            return@Interceptor response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
        }

        val offlineInterceptor = Interceptor{chain ->
            var request: Request = chain.request()

            if (!NetworkUtils.isNetworkAvailable()) {
                val cacheControl =  CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
            }

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(offlineInterceptor)
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
