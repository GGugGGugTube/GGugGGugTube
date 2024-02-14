package com.example.myapplication.youtubeApi

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


object ShortsUtils {
    private val TAG = "ShortUtils"
    fun isShorts(youtubeResource: YoutubeVideoResource): Boolean {
        val videoId = youtubeResource.id
        return youtubeHeadRequest(videoId)
    }
    fun isShorts(youtubeSearchResource: YoutubeVideoSearchResource): Boolean {
        val videoId = youtubeSearchResource.id.videoId
        return youtubeHeadRequest(videoId)
    }

    private fun youtubeHeadRequest(videoId:String):Boolean{
        var urlConnection: HttpURLConnection? = null
        System.setProperty("http.keepAlive", "false")
        try {
            val url = URL("http://youtube.com/shorts/$videoId")
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "HEAD"

            val response = urlConnection.responseCode
            if (response == 200) {
                Log.d(TAG, "video(id: $videoId) is shorts")
                return true
            } else if (response == 303) {
                Log.d(TAG, "video(id: $videoId) is a video")
                return false
            }

            urlConnection.inputStream.close()
        } catch (e: MalformedURLException) {
            e.printStackTrace();
        } catch (e: IOException) {
            e.printStackTrace();
        } finally {
            urlConnection?.disconnect()
            return false
        }
    }
}