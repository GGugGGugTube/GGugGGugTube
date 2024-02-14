package com.example.myapplication.naverdictionary

import com.example.myapplication.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverRetrofit {

    val naverApiService: NaverAPI
        get() = instance.create(NaverAPI::class.java)

    private val instance: Retrofit
        private get() {
            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(Constants.NAVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}