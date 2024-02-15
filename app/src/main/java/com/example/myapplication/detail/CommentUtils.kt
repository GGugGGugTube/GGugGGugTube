package com.example.myapplication.detail

import android.content.Context
import com.example.myapplication.MyApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CommentUtils {
    fun getVideoComments(videoId: String) = getComments().filter { it.videoId == videoId }

    fun saveComment(newComment: Comment) {
        val context = MyApplication.appContext!!
        val prefs =
            context.getSharedPreferences(
                CommentConstants.COMMENTS_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val json = Gson().toJson(getComments() + newComment)
        prefs.edit().putString(CommentConstants.COMMENTS_PREF_KEY, json).apply()
    }

    private fun getComments(): List<Comment> {
        val context = MyApplication.appContext!!
        val prefs =
            context.getSharedPreferences(
                CommentConstants.COMMENTS_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val json = prefs.getString(CommentConstants.COMMENTS_PREF_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Comment>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

}