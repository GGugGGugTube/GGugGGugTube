package com.example.myapplication.like

import android.content.Context
import com.example.myapplication.CtItem
import com.example.myapplication.MyApplication
import com.example.myapplication.YoutubeVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object LikedUtils {
    fun saveLikedVideos(videos: List<YoutubeVideo>) {
        val context = MyApplication.appContext!!

        val prefs =
            context.getSharedPreferences(
                LikedConstants.LIKED_VIDEOS_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val json = Gson().toJson(videos)
        prefs.edit().putString(LikedConstants.LIKED_VIDEOS_PREF_KEY, json).apply()
    }

    fun getLikedVideos(): List<YoutubeVideo> {
        val context = MyApplication.appContext!!

        val prefs =
            context.getSharedPreferences(
                LikedConstants.LIKED_VIDEOS_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val json = prefs.getString(LikedConstants.LIKED_VIDEOS_PREF_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<YoutubeVideo>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun getAnimalLikedVideos(animal: CtItem.CategoryItem):List<YoutubeVideo> =
        getLikedVideos().filter { it.categoryId ==  animal.Id}
}