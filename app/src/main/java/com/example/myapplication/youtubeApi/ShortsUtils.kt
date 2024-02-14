package com.example.myapplication.youtubeApi

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.apache.http.HttpResponse
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


object ShortsUtils {
    private val TAG = "ShortUtils"
    suspend fun isShorts(youtubeResource: YoutubeVideoResource): Boolean {
        val videoId = youtubeResource.id
        return youtubeHeadRequest(videoId)
    }

    suspend fun isShorts(youtubeSearchResource: YoutubeVideoSearchResource): Boolean {
        val videoId = youtubeSearchResource.id.videoId
        return youtubeHeadRequest(videoId)
    }

    private suspend fun youtubeHeadRequest(videoId: String): Boolean {
        return CoroutineScope(Dispatchers.IO).async{
            var urlConnection: HttpURLConnection? = null
            System.setProperty("http.keepAlive", "false")
            try {
                val url = URL("https://www.youtube.com/shorts/${videoId}")


                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.instanceFollowRedirects = false
                urlConnection.requestMethod = "HEAD"
                urlConnection.connect()

                val response = urlConnection.responseCode
                Log.d(TAG, "response: ${response}")

                Log.d(TAG, "url: ${url}")
                Log.d(TAG, "getHeaderField(\"Location\"): ${urlConnection.getHeaderField("Location")}")
                if ((response == 200) || (urlConnection.getHeaderField("Location") == url.toString())) {
                    Log.d(TAG, "video(id: $videoId) is shorts")
                    return@async true
                } else {
                    Log.d(TAG, "video(id: $videoId) is a video")
                    return@async false
                }

            } catch (e: MalformedURLException) {
                e.printStackTrace();
            } catch (e: IOException) {
                e.printStackTrace();
            } finally {
                urlConnection?.disconnect()
            }
            return@async false
        }.await()
    }
}