//package com.example.myapplication.youtubeApi
//
//class YouTubeRepository(private val youtubeApiService: YoutubeApiService) {
//
//    suspend fun searchVideos(query: String, apiKey: String): YoutubeApiResponse {
//        // 검색에 필요한 쿼리 맵 구성
//        val queryMap = mapOf(
//            "part" to "snippet",
//            "q" to query,
//            "type" to "video",
//            "key" to apiKey
//        )
//
//        // YoutubeApiService의 searchVideos 함수를 호출하여 검색 결과를 받아옴
//        return youtubeApiService.searchVideos("snippet", query, "video", apiKey)
//    }
//
//    suspend fun getVideoDetails(videoId: String, apiKey: String): YoutubeVideoDetailsResponse {
//        // YoutubeApiService의 getVideoDetails 함수를 호출하여 상세 정보를 받아옴
//        return youtubeApiService.getVideoDetails("snippet", videoId, apiKey)
//    }
//}