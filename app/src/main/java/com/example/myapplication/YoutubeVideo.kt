package com.example.myapplication

import android.os.Parcelable
import android.util.Log
import com.example.myapplication.search.CategoryItemManager
import com.example.myapplication.youtubeApi.ShortsUtils
import com.example.myapplication.youtubeApi.StatisticsUtils
import com.example.myapplication.youtubeApi.YoutubeVideoResource
import com.example.myapplication.youtubeApi.YoutubeVideoSearchResource
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeVideo(
    val id: String,
    val title: String,
    val author: String, //동영상 작성자
    val viewCount: Int, //동영상 조회수
    val description: String, //동영상 상세 정보
    val thumbnail: String,
    val publishedAt: String,
    val categoryId: Int,
    val isShorts: Boolean,
    var isLiked: Boolean = false
) : Parcelable {
    companion object {
        private val TAG = "YoutubeVideo"
        suspend fun createYouTubeVideo(
            categoryId: Int = CategoryItemManager.DEFAULT_CATEGORY_ID,
            youtubeVideoResource: YoutubeVideoResource
        ): YoutubeVideo {
            return with(youtubeVideoResource) {
                val id = this.id
                val title = this.snippet.title
                val author = this.snippet.channelTitle
                val viewCount = StatisticsUtils.getViewCount(id)
                Log.d(TAG, "author: $author, viewCount: $viewCount")
                val description = this.snippet.description
                val thumbnail = this.snippet.thumbnails.high.url
                val publishedAt = this.snippet.publishedAt
                val categoryId = categoryId
                val isShorts = ShortsUtils.isShorts(id)

                YoutubeVideo(
                    id,
                    title,
                    author,
                    viewCount,
                    description,
                    thumbnail,
                    publishedAt,
                    categoryId,
                    isShorts
                )
            }
        }

        suspend fun createYouTubeVideo(
            categoryID: Int = CategoryItemManager.DEFAULT_CATEGORY_ID,
            youtubeVideoSearchResource: YoutubeVideoSearchResource
        ): YoutubeVideo {
            return with(youtubeVideoSearchResource) {
                val id = this.id.videoId
                val title = this.snippet.title
                val author = this.snippet.channelTitle
                val viewCount = StatisticsUtils.getViewCount(id)
                val description = this.snippet.description
                val thumbnail = this.snippet.thumbnails.high.url
                val publishedAt = this.snippet.publishedAt
                val categoryId = categoryID
                val isShorts = ShortsUtils.isShorts(id)

                YoutubeVideo(
                    id,
                    title,
                    author,
                    viewCount,
                    description,
                    thumbnail,
                    publishedAt,
                    categoryId,
                    isShorts
                )
            }
        }
    }
}