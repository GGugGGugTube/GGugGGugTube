package com.example.myapplication.like

import com.example.myapplication.YoutubeVideo
import com.example.myapplication.watchlist.WatchListUtils

object LikedWatchListUtils {
    fun updateWatchedVideoLike(youtubeVideo: YoutubeVideo, isLiked: Boolean) {
        val watchedList = WatchListUtils.getVideoWatchList() + WatchListUtils.getShortsWatchList()
        watchedList.forEach {
            if (it.id == youtubeVideo.id) it.isLiked = isLiked
        }
        WatchListUtils.saveWatchList(watchedList)
    }
}