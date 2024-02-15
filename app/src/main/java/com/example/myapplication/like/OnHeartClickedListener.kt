package com.example.myapplication.like

import android.util.Log
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.watchlist.WatchListUtils

object OnHeartClickedListener {
    private val TAG = "OnHeartClickedInterface"
    fun onHeartClicked(youtubeVideo: YoutubeVideo) {
        val likedVideos = LikedUtils.getLikedVideos().toMutableList()
        Log.d(TAG, "before_likedVideos.size = ${likedVideos.size}")

        if (youtubeVideo.isLiked) {
            if(!LikedUtils.isSavedInLikedVideos(youtubeVideo.id)){
                likedVideos.add(youtubeVideo)
            }

            if(WatchListUtils.isSavedInWatchList(youtubeVideo.id))
                LikedWatchListUtils.updateWatchedVideoLike(youtubeVideo, true)
        } else{
            likedVideos.find{it.id == youtubeVideo.id}?.let{
                likedVideos.remove(it)
            }

            if(WatchListUtils.isSavedInWatchList(youtubeVideo.id))
                LikedWatchListUtils.updateWatchedVideoLike(youtubeVideo, false)
        }

        LikedUtils.saveLikedVideos(likedVideos)
        Log.d(TAG, "after_likedVideos.size = ${likedVideos.size}")
    }
}