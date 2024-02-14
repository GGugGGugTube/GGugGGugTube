package com.example.myapplication.like

import android.util.Log
import com.example.myapplication.YoutubeVideo

open class OnHeartClickedListener {
    private val TAG = "OnHeartClickedInterface"

    fun onHeartClicked(youtubeVideo: YoutubeVideo) {
        val likedVideos = LikedUtils.getLikedVideos()
        Log.d(TAG, "before_likedVideos.size = ${likedVideos.size}")

        LikedUtils.saveLikedVideos(
            if (youtubeVideo.isLiked) likedVideos + youtubeVideo
            else likedVideos.filter { it != youtubeVideo }
        )
        Log.d(TAG, "after_likedVideos.size = ${likedVideos.size}")
    }
}