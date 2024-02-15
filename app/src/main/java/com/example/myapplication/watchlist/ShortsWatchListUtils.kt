package com.example.myapplication.watchlist

import android.content.Context
import com.example.myapplication.MyApplication
import com.example.myapplication.YoutubeVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ShortsWatchListUtils {
    private const val SHORTS_WATCH_LIST_PREFS_NAME = "shorts_watch_list_prefs"
    private const val SHORTS_WATCH_LIST_PREF_KEY = "shorts_watch_list"

    fun saveShortsWatchList(videos: List<YoutubeVideo>) {
        val context = MyApplication.appContext!!
        val prefs = context.getSharedPreferences(SHORTS_WATCH_LIST_PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(videos)
        prefs.edit().putString(SHORTS_WATCH_LIST_PREF_KEY, json).apply()
    }

    fun getShortsWatchList(): List<YoutubeVideo> {
        val context = MyApplication.appContext!!
        val prefs = context.getSharedPreferences(SHORTS_WATCH_LIST_PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(SHORTS_WATCH_LIST_PREF_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<YoutubeVideo>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun clearShortsWatchList() {
        val context = MyApplication.appContext!!
        val prefs = context.getSharedPreferences(SHORTS_WATCH_LIST_PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(SHORTS_WATCH_LIST_PREF_KEY).apply()
    }
}