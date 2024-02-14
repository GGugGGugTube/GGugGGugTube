package com.example.myapplication

import android.os.Parcelable
import com.example.myapplication.youtubeApi.ShortsUtils
import com.example.myapplication.youtubeApi.YoutubeVideoResource
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
            youtubeVideoResource: YoutubeVideoResource
        ): YoutubeVideo {
            with(youtubeVideoResource) {
                val title = this.snippet.title
                val description = this.snippet.description
                val thumbnail = this.snippet.thumbnails.default.url
                val publishedAt = this.snippet.publishedAt

                val category = "" //TODO : 동물 카테고리 저장하기
                val isShorts = ShortsUtils.isShorts(youtubeVideoResource)

                return YoutubeVideo(title, description, thumbnail, publishedAt, category, isShorts)
            }
        }

        fun createYouTubeVideo(
            category: String = "",
            youtubeVideoSearchResource: YoutubeVideoSearchResource
        ): YoutubeVideo {
            with(youtubeVideoSearchResource) {
                val title = this.snippet.title
                val description = this.snippet.description
                val thumbnail = this.snippet.thumbnails.default.url
                val publishedAt = this.snippet.publishedAt

                val category = category //TODO : 동물 카테고리 저장하기
                val isShorts = ShortsUtils.isShorts(this)
                return YoutubeVideo(title, description, thumbnail, publishedAt, category, isShorts)
            }
        }
    }
}