package com.example.myapplication.naverdictionary

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverAPI {
    @GET("v1/search/encyc.json")

    suspend fun naverDic(
        @Header("X-Naver-Client-Id") Id: String?,
        @Header("X-Naver-Client-Secret") Secret: String?,
        @Query("query") query: String
    ): Call<NaverData>
}