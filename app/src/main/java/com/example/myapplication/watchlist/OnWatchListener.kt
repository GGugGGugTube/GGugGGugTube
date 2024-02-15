package com.example.myapplication.watchlist

import android.util.Log
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.like.LikedUtils

object OnWatchListener {

    fun onWatch(youtubeVideo: YoutubeVideo) {
        if (!youtubeVideo.isShorts) {
            val watchedVideos = WatchListUtils.getVideoWatchList().toMutableList()
            watchedVideos.find { it.id == youtubeVideo.id } ?: watchedVideos.add(youtubeVideo)
            WatchListUtils.saveWatchList(watchedVideos)
        } else {
            val watchedShorts = WatchListUtils.getShortsWatchList().toMutableList()
            watchedShorts.find { it.id == youtubeVideo.id } ?: watchedShorts.add(youtubeVideo)
            WatchListUtils.saveWatchList(watchedShorts)
        }
    }

    fun onWatchClearVideo() {
        WatchListUtils.removeWatchList(WatchListUtils.getVideoWatchList())
    }

    fun onWatchClearShorts() {
        WatchListUtils.removeWatchList(WatchListUtils.getShortsWatchList())
    }
}


