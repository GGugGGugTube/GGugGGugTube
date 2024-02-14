package com.example.myapplication.like

import android.util.Log
import com.example.myapplication.YoutubeVideo

object OnHeartClickedListener {
    private val TAG = "OnHeartClickedInterface"
    fun onHeartClicked(youtubeVideo: YoutubeVideo) {
        val likedVideos = LikedUtils.getLikedVideos().toMutableList()
        Log.d(TAG, "before_likedVideos.size = ${likedVideos.size}")

        if (youtubeVideo.isLiked) likedVideos.add(youtubeVideo)
        else likedVideos.remove(youtubeVideo)

        LikedUtils.saveLikedVideos(likedVideos)
        Log.d(TAG, "after_likedVideos.size = ${likedVideos.size}")
    }
}