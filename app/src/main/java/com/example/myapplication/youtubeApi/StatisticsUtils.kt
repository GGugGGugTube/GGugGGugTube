package com.example.myapplication.youtubeApi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

object StatisticsUtils {
    suspend fun getViewCount(videoId: String): Int = CoroutineScope(Dispatchers.IO).async {
        val statisticsResponse = YoutubeNetworkClient.youtubeNetWork.getStatistics(videoId)
        statisticsResponse.items[0].statistics.viewCount
    }.await()
}