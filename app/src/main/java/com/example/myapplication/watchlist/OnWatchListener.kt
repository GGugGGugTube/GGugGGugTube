package com.example.myapplication.watchlist

import android.util.Log
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.like.LikedUtils

object OnWatchListener {
    private val TAG = "OnWatchListener"

    fun onWatch(youtubeVideo: YoutubeVideo) {
        val watchedVideos = WatchListUtils.getWatchList().toMutableList()
        Log.d(TAG, "before_watchedVideos.size = ${watchedVideos.size}")

        if(!watchedVideos.contains(youtubeVideo))
            watchedVideos.add(youtubeVideo)

        LikedUtils.saveLikedVideos(watchedVideos)
        Log.d(TAG, "after_watchedVideos.size = ${watchedVideos.size}")
    }

    fun onWatchClear(){
        WatchListUtils.clearWatchList()
    }
}

