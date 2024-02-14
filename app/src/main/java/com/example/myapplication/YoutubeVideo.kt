package com.example.myapplication

import android.os.Parcelable
import com.example.myapplication.youtubeApi.YoutubeVideoResourceSnippet
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeVideo(
    val title: String,
    val description: String,
    val thumbnails: String,
    val publishedAt: String,
    val category: String,
    val isShorts: Boolean,
    var isLiked: Boolean = false
): Parcelable {
    companion object {
        fun createYouTubeVideo(youtubeSnippet: YoutubeVideoResourceSnippet): YoutubeVideo {
            val title = youtubeSnippet.title
            val description = youtubeSnippet.description
            val thumbnails = youtubeSnippet.thumbnails.url
            val publishedAt = youtubeSnippet.publishedAt
            val category = "" //TODO : 동물 카테고리 저장하기
            val isShorts = false //TODO: 동영상이 숏츠인지 영상인지 판별하기
            return YoutubeVideo(title, description, thumbnails, publishedAt, category, isShorts)
        }
    }
}