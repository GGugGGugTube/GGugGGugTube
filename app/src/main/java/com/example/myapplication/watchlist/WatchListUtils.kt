package com.example.myapplication.watchlist

import android.content.Context
import com.example.myapplication.YoutubeVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WatchListUtils {

    fun saveWatchList(context: Context, videos: List<YoutubeVideo>) {
        val prefs =
            context.getSharedPreferences(WatchList.WATCH_LIST_PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(videos)
        prefs.edit().putString(WatchList.WATCH_LIST_PREF_KEY, json).apply()
    }

    fun getWatchList(context: Context): List<YoutubeVideo> {
        val prefs =
            context.getSharedPreferences(WatchList.WATCH_LIST_PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(WatchList.WATCH_LIST_PREF_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<YoutubeVideo>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun clearWatchList(context: Context) {
        val prefs =
            context.getSharedPreferences(WatchList.WATCH_LIST_PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(WatchList.WATCH_LIST_PREF_KEY).apply()
    }
}

