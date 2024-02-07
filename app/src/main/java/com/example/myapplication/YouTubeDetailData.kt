package com.example.myapplication

data class YoutubeVideoDetailsResponse(
    val items: List<YoutubeVideoDetailsItem>
)

data class YoutubeVideoDetailsItem(
    val snippet: YoutubeVideoSnippet,
    val statistics: YoutubeVideoStatistics
)

data class YoutubeVideoSnippet(
    val title: String,
    val publishedAt: String,
    val channelId: String,
    val channelTitle: String,
)

data class YoutubeVideoStatistics(
    val viewCount: String,
)