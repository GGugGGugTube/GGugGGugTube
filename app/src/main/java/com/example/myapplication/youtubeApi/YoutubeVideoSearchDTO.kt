package com.example.myapplication.youtubeApi

import com.google.api.services.youtube.model.LiveBroadcast
import com.google.api.services.youtube.model.Playlist
import com.google.common.primitives.UnsignedInteger

data class YoutubeVideoSearchResponse(
    val kind: String = "youtube#searchListResponse",
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val regionCode: String,
    val pageInfo: YoutubeVideoSearchPageInfo,
    val items: MutableList<YoutubeVideoSearchResource> //@Query("part") part = "snippet"
)

data class YoutubeVideoSearchPageInfo(
    val totalResults: Int,
    val resultsPerPage: Int,
)

data class YoutubeVideoSearchResource(
    val kind:String = "youtube#searchResult",
    val etag:String,
    val id: YoutubeVideoSearchResourceId,
    val snippet: YoutubeVideoSearchResourceSnippet
)

data class YoutubeVideoSearchResourceId(
    val kind:String,
    val videoId:String,
    val channelId: String,
    val playlistId:String
)
data class YoutubeVideoSearchResourceSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: YoutubeVideoSearchResourceThumbnails,
    val channelTitle: String,
    val liveBroadcastContent:String
)

data class YoutubeVideoSearchResourceThumbnails(
    val default: YoutubeVideoSearchResourceThumbnail,
    val medium: YoutubeVideoSearchResourceThumbnail,
    val high: YoutubeVideoSearchResourceThumbnail
)

data class YoutubeVideoSearchResourceThumbnail(
    val url:String,
    val width:Long,
    val height:Long
)