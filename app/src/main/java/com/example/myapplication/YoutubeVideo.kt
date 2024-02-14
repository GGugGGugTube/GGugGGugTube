package com.example.myapplication

import android.os.Parcelable
import android.util.Log
import com.example.myapplication.youtubeApi.ShortsUtils
import com.example.myapplication.youtubeApi.YoutubeVideoResource
import com.example.myapplication.youtubeApi.YoutubeVideoResourceSnippet
import com.example.myapplication.youtubeApi.YoutubeVideoSearchResource
import com.example.myapplication.youtubeApi.YoutubeVideoSearchResourceSnippet
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeVideo(
    val id:String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val publishedAt: String,
    val category: String,
    val isShorts: Boolean,
    var isLiked: Boolean = false
) : Parcelable {
    companion object {
        suspend fun createYouTubeVideo(
            category: String = "",
            youtubeVideoResource: YoutubeVideoResource
        ): YoutubeVideo {
            return with(youtubeVideoResource) {
                val id = this.id
                val title = this.snippet.title
                val description = this.snippet.description
                val thumbnail = this.snippet.thumbnails.default.url
                val publishedAt = this.snippet.publishedAt

                val category = category
                val isShorts = ShortsUtils.isShorts(youtubeVideoResource)

                YoutubeVideo(id, title, description, thumbnail, publishedAt, category, isShorts)
            }
        }

        suspend fun createYouTubeVideo(
            category: String = "",
            youtubeVideoSearchResource: YoutubeVideoSearchResource
        ): YoutubeVideo {
            return with(youtubeVideoSearchResource) {
                val id = this.id.videoId
                val title = this.snippet.title
                val description = this.snippet.description
                val thumbnail = this.snippet.thumbnails.default.url
                val publishedAt = this.snippet.publishedAt

                val category = category
                val isShorts = ShortsUtils.isShorts(this)

                YoutubeVideo(id, title, description, thumbnail, publishedAt, category, isShorts)
            }
        }
    }
}