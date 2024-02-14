package com.example.myapplication.youtubeApi

import com.google.common.primitives.UnsignedInteger

data class YoutubeVideoResponse(
    val kind: String = "youtube#videoListResponse",
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val pageInfo: YoutubeVideoPageInfo,
    val items: MutableList<YoutubeVideoResource> //@Query("part") part = "snippet"
)

data class YoutubeVideoPageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)
data class YoutubeVideoResource(
    val kind:String = "youtube#video",
    val etag:String,
    val id:String,
    val snippet: YoutubeVideoResourceSnippet
)
data class YoutubeVideoResourceSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: YoutubeVideoResourceThumbnails,
    val channelTitle:String,
    val tags: MutableList<String>,
    val categoryId:String,
    val liveBroadcastContent:String,
    val defaultLanguage:String,
    val localized: YoutubeVideoResourceLocalized,
    val defaultAudioLanguage:String
)

data class YoutubeVideoResourceThumbnails(
    val default: YoutubeVideoResourceThumbnail,
    val medium: YoutubeVideoResourceThumbnail,
    val high: YoutubeVideoResourceThumbnail,
    val standard: YoutubeVideoResourceThumbnail,
    val maxres: YoutubeVideoResourceThumbnail? = null
)
data class YoutubeVideoResourceThumbnail(
    val url:String,
    val width:Long,
    val height:Long
)

data class YoutubeVideoResourceLocalized(
    val title:String,
    val description: String
)
