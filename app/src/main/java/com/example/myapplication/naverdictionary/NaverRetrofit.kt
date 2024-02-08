package com.example.myapplication.naverdictionary

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
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}