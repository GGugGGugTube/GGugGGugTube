package com.example.myapplication.youtubeApi

import retrofit2.http.GET
import retrofit2.http.Query

const val PET_AND_ANIMALS_CATEGORY_ID: String = "15"

interface YoutubeNetWorkInterface {
    //Pet&Animals 카테고리의 인기 동영상 목록 가져오기
    @GET("videos?key=AIzaSyDwJ6e9yH7IIr2deX2kV26T2T5P7254lVE")
    suspend fun getMostPopularPetAndAnimals(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular", //차트: 인기 차트
        @Query("hl") hl: String = "ko", //언어: 한국어
        @Query("regionCode") regionCode: String = "kr", //지역 코드: 한국
        @Query("videoCategoryId") videoCategoryId: String = PET_AND_ANIMALS_CATEGORY_ID,
        @Query("maxResults") maxResults: Int = 50 //결과 item 최대 수
    ): YoutubeVideoResponse

    //Pet&Animals 카테고리에서 검색 결과 목록 가져오기
    @GET("search?key=AIzaSyDwJ6e9yH7IIr2deX2kV26T2T5P7254lVE")
    suspend fun getSearchedPetAndAnimals(
        @Query("q") q: String, //검색어
        @Query("part") part: String = "snippet",
        @Query("type") type: String = "video",
        @Query("videoCategoryID") videoCategoryId: String = PET_AND_ANIMALS_CATEGORY_ID,
        @Query("order") order: String = "date", //결과 최신순 정렬
        @Query("safeSearch") safeSearch: String = "strict", //제한된 컨텐츠 모두 제외
        @Query("maxResults") maxResults: Int = 50 //결과 item 최대 수
    ):YoutubeVideoSearchResponse

    //동영상 통계 정보 가져오기
    @GET("videos?key=AIzaSyDwJ6e9yH7IIr2deX2kV26T2T5P7254lVE")
    suspend fun getStatistics(
        @Query("id") id:String, //동영상 ID
        @Query("part") part: String = "statistics"
    ): YoutubeVideoStatisticsResponse
}