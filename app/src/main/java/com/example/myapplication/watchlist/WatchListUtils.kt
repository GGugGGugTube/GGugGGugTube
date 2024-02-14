package com.example.myapplication.watchlist

import android.content.Context
import com.example.myapplication.MyApplication
import com.example.myapplication.YoutubeVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WatchListUtils {
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

    fun getWatchList(): List<YoutubeVideo> {
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

    fun clearWatchList() {
        val context = MyApplication.appContext!!

        val prefs =
            context.getSharedPreferences(
                WatchListConstants.WATCH_LIST_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        prefs.edit().remove(WatchListConstants.WATCH_LIST_PREF_KEY).apply()
    }
}

