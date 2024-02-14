package com.example.myapplication.youtubeApi

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.IOException
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_MOVED_PERM
import java.net.HttpURLConnection.HTTP_MOVED_TEMP
import java.net.HttpURLConnection.HTTP_SEE_OTHER
import java.net.MalformedURLException
import java.net.URL


object ShortsUtils {
    private val TAG = "ShortUtils"
    suspend fun isShorts(videoId: String): Boolean {
        return youtubeHeadRequest(videoId)
    }

    private suspend fun youtubeHeadRequest(videoId: String): Boolean {
        return CoroutineScope(Dispatchers.IO).async {
            var urlConnection: HttpURLConnection? = null
            System.setProperty("http.keepAlive", "false")

            try {
                val url = URL("https://www.youtube.com/shorts/${videoId}")

                if(getFinalURL(url) == url){
                    Log.d(TAG, "video(id: $videoId) is shorts")
                    return@async true
                }else{
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
    private fun getFinalURL(url: URL): URL? {
        try {
            val con = url.openConnection() as HttpURLConnection
            con.instanceFollowRedirects = false
            con.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36"
            )
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8")
            con.addRequestProperty("Referer", "https://www.google.com/")
            con.connect()

            val resCode = con.responseCode
            if (resCode == HTTP_SEE_OTHER || resCode == HTTP_MOVED_PERM || resCode == HTTP_MOVED_TEMP) {
                var Location = con.getHeaderField("Location")
                if (Location.startsWith("/")) {
                    Location = url.protocol + "://" + url.host + Location
                }
                return getFinalURL(URL(Location))
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return url
    }
}
