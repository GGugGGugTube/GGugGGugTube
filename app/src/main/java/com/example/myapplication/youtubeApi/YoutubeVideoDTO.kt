package com.example.myapplication.youtubeApi

import com.google.common.primitives.UnsignedInteger

data class YoutubeVideoDTO(
    val kind: String = "youtube#videoListResponse",
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val pageInfo: YoutubeVideoPageInfo,
    val items: MutableList<YoutubeVideoResourceSnippet> //@Query("part") part = "snippet"
)

data class YoutubeVideoPageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)

//YoutubeVideoResource.snippet
data class YoutubeVideoResourceSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: YoutubeVideoResourceThumbnail,
    val channelTitle:String,
    val tags: MutableList<String>,
    val categoryId:String,
    val liveBroadcastContent:String,
    val defaultLanguage:String,
    val localized: YoutubeVideoResourceLocalized,
    val defaultAudioLanguage:String
)

data class YoutubeVideoResourceThumbnail(
    val url:String,
    val width:UnsignedInteger,
    val height:UnsignedInteger
)

data class YoutubeVideoResourceLocalized(
    val title:String,
    val description: String
)
