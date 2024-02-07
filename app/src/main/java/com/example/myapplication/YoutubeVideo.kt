package com.example.myapplication

data class YoutubeVideo(
    val title: String,
    val description: String,
    val thumbnails: String,
    val publishedAt: String,
    val category: String,
    val isShorts: Boolean,
    var isLiked: Boolean = false


)  {
    companion object {
        fun createYouTubeVideo(youtubeSnippet: YoutubeSnippet): YoutubeVideo {
            val title = youtubeSnippet.title
            val description = youtubeSnippet.description
            val thumbnails = youtubeSnippet.thumbnails.default.url
            val publishedAt = youtubeSnippet.publishedAt
            val category = ""
            val isShorts = false
//            TODO: 카테고리 지정하기, 숏츠 인지 확인하기
            return YoutubeVideo(title, description, thumbnails, publishedAt, category, isShorts)

        }
    }
}