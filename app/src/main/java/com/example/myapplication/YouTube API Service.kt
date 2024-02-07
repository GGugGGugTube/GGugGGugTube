package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("search")

    //YouTube에서 동영상을 검색하는 데 사용
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") apiKey: String
    ): YoutubeApiResponse


    //동영상의 세부 정보를 가져오는 데 사용
    @GET("videos")
    suspend fun getVideoDetails(
        @Query("part") part: String = "snippet",
        @Query("id") videoId: String,
        @Query("key") apiKey: String
    ): YoutubeVideoDetailsResponse


    companion object {
        fun create(): YoutubeApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(YoutubeApiService::class.java)
        }
    }
}