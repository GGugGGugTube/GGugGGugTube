package com.example.myapplication.youtubeApi

import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeNetWorkInterface {

    @GET("videos?key=AIzaSyB342voTWUdwtQzooloaZTYutUzV3H_xKs")
    suspend fun getMostPopularPetAndAnimals(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular", //차트: 인기 차트
        @Query("hl") hl:String = "ko", //언어: 한국어
        @Query("maxResults") maxResults:Int = 25, //결과 item 최대 수
        @Query("regionCode") regionCode:String = "kr", //지역 코드: 한국
        @Query("videoCategoryId") videoCategoryId:String = "15" //카테고리: 15(Pets & Animals)
        ): YoutubeVideoDTO
}