package com.example.myapplication.youtubeApi

import com.google.api.services.youtube.model.LiveBroadcast
import com.google.common.primitives.UnsignedInteger

data class YoutubeVideoSearchResponse(
    val kind: String = "youtube#searchListResponse",
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val regionCode: String,
    val pageInfo: YoutubeVideoSearchPageInfo,
    val items: MutableList<YoutubeVideoSearchResourceSnippet> //@Query("part") part = "snippet"
)

data class YoutubeVideoSearchPageInfo(
    val totalResults: Int,
    val resultsPerPage: Int,
)

//YoutubeVideoSearchResource.snippet
data class YoutubeVideoSearchResourceSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: YoutubeVideoSearchResourceThumbnail,
    val channelTitle: String,
    val liveBroadcastContent:String
)

data class YoutubeVideoSearchResourceThumbnail(
    val url: String,
    val width: UnsignedInteger,
    val height: UnsignedInteger
)
