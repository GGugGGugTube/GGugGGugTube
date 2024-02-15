package com.example.myapplication.watchlist

import android.content.Context
import com.example.myapplication.MyApplication
import com.example.myapplication.YoutubeVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WatchListUtils {

    fun isSavedInWatchList(videoId: String): Boolean =
        (getWatchList().find { it.id == videoId } != null)

    fun saveWatchList(videos: List<YoutubeVideo>) {
        val context = MyApplication.appContext!!

        val prefs =
            context.getSharedPreferences(
                WatchListConstants.WATCH_LIST_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val json = Gson().toJson(videos)
        prefs.edit().putString(WatchListConstants.WATCH_LIST_PREF_KEY, json).apply()
    }

    fun removeWatchList(videos: List<YoutubeVideo>) {
        val newWatchList = getWatchList().filter { watchedVideo ->
            videos.find { it.id == watchedVideo.id } == null
        }
        saveWatchList(newWatchList)
    }

    private fun getWatchList(): List<YoutubeVideo> {
        val context = MyApplication.appContext!!

        val prefs =
            context.getSharedPreferences(
                WatchListConstants.WATCH_LIST_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val json = prefs.getString(WatchListConstants.WATCH_LIST_PREF_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<YoutubeVideo>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun getVideoWatchList(): List<YoutubeVideo> = getWatchList().filter { !it.isShorts }
    fun getShortsWatchList(): List<YoutubeVideo> = getWatchList().filter { it.isShorts }
}

