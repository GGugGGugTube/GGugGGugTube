package com.example.myapplication.like

import android.content.Context
import com.example.myapplication.YoutubeVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LikedUtils {

    fun saveLikedVideos(context: Context, videos: List<YoutubeVideo>) {
        val prefs =
            context.getSharedPreferences(Liked.LIKED_VIDEOS_PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(videos)
        prefs.edit().putString(Liked.LIKED_VIDEOS_PREF_KEY, json).apply()
    }

    fun getLikedVideos(context: Context): List<YoutubeVideo> {
        val prefs =
            context.getSharedPreferences(Liked.LIKED_VIDEOS_PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(Liked.LIKED_VIDEOS_PREF_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<YoutubeVideo>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }
}