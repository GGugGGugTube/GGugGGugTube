package com.example.myapplication.like

import android.content.Context
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


    private fun getRemainItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getDogItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getCatItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getParrotItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getElephantItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getGireffeItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getPandaItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getCrocodileItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getHamsterItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getKangarooItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getCamelItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getSheepItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getWhaleItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getWolfItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getMonkeyItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getBearItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getRabbitItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getPenguinItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getOthersItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }
}