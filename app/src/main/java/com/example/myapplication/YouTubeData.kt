package com.example.myapplication

data class YoutubeApiResponse(
    val items: List<YoutubeVideoItem>,

    )

data class YoutubeVideoItem(
    val id: YoutubeVideoId,
    val snippet: YoutubeSnippet,
    var liked: Boolean = false
)

data class YoutubeVideoId(
    val videoId: String
)

data class YoutubeSnippet(
    val title: String,
    val description: String,
    val thumbnails: YoutubeThumbnails,
    val publishedAt: String

)

data class YoutubeThumbnails(
    val default: YoutubeThumbnail
)

data class YoutubeThumbnail(
    val url: String
)