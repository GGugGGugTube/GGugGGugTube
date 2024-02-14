package com.example.myapplication

import android.os.Parcelable
import com.example.myapplication.youtubeApi.YoutubeVideoResourceSnippet
import com.example.myapplication.youtubeApi.YoutubeVideoSearchResource
import com.example.myapplication.youtubeApi.YoutubeVideoSearchResourceSnippet
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeVideo(
    val title: String,
    val description: String,
    val thumbnail: String,
    val publishedAt: String,
    val category: String,
    val isShorts: Boolean,
    var isLiked: Boolean = false
) : Parcelable {
    companion object {
        fun createYouTubeVideo(
            category: String = "",
            youtubeSnippet: YoutubeVideoResourceSnippet
        ): YoutubeVideo {
            val title = youtubeSnippet.title
            val description = youtubeSnippet.description
            val thumbnail = youtubeSnippet.thumbnails.default.url
            val publishedAt = youtubeSnippet.publishedAt

            val category = "" //TODO : 동물 카테고리 저장하기
            val isShorts = false //TODO: 동영상이 숏츠인지 영상인지 판별하기
            return YoutubeVideo(title, description, thumbnail, publishedAt, category, isShorts)
        }

        fun createYouTubeVideo(
            category: String = "",
            youtubeSnippet: YoutubeVideoSearchResourceSnippet
        ): YoutubeVideo {
            val title = youtubeSnippet.title
            val description = youtubeSnippet.description
            val thumbnail = youtubeSnippet.thumbnails.default.url
            val publishedAt = youtubeSnippet.publishedAt

            val category = category //TODO : 동물 카테고리 저장하기
            val isShorts = false //TODO: 동영상이 숏츠인지 영상인지 판별하기
            return YoutubeVideo(title, description, thumbnail, publishedAt, category, isShorts)
        }
    }
}