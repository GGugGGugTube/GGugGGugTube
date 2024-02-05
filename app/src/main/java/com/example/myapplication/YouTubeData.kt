package com.example.myapplication

data class YoutubeApiResponse(
    val items: List<YoutubeVideoItem>
)

data class YoutubeVideoItem(
    val id: YoutubeVideoId,
    val snippet: YoutubeSnippet
)

data class YoutubeVideoId(
    val videoId: String
)

data class YoutubeSnippet(
    val title: String,
    val description: String,
    val thumbnails: YoutubeThumbnails
)

data class YoutubeThumbnails(
    val default: YoutubeThumbnail
)

data class YoutubeThumbnail(
    val url: String
)