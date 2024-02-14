package com.example.myapplication.youtubeApi

data class YoutubeVideoStatisticsResponse(
    val kind: String = "youtube#videoListResponse",
    val etag: String,
    val items: MutableList<YoutubeVideoStatisticsResource>, //@Query("part") part = "statistics"
    val pageInfo: YoutubeVideoStatisticsPageInfo,
)

data class YoutubeVideoStatisticsResource(
    val kind: String = "youtube#video",
    val etag: String,
    val id: String,
    val statistics: YoutubeVideoStatistics
)

data class YoutubeVideoStatistics(
    val viewCount: Int,
    val likeCount: Int,
    val favoriteCount: Int,
    val commentCount: Int
)

data class YoutubeVideoStatisticsPageInfo(
    val totalResults: Int,
    val resultsPerPage: Int,
)
