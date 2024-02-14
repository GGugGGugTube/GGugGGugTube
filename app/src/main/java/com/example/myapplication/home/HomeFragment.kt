package com.example.myapplication.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.youtubeApi.YoutubeNetworkClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    private lateinit var binding: FragmentHomeBinding
    private lateinit var shortsadapter: HomeShortsAdapter
    private lateinit var videoadapter: HomeVideoAdapter
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        shortsView()
        videoView()

        return binding.root
    }

    private fun shortsView() {
        shortsadapter = HomeShortsAdapter(mContext)
        binding.reHomeBestShorts.adapter = shortsadapter
        binding.reHomeBestShorts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun videoView() {
        videoadapter = HomeVideoAdapter(mContext)
        binding.reHomeVideo.adapter = videoadapter
        binding.reHomeVideo.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchYoutubeResult()
    }

    private fun fetchYoutubeResult() = lifecycleScope.launch {
        Log.d(TAG, "fetching youtube search response...")
        var searchResponse = async {
            YoutubeNetworkClient.youtubeNetWork.getMostPopularPetAndAnimals()
        }

        val youtubeSearchResult = mutableListOf<YoutubeVideo>()
        Log.d(TAG, "result size: ${searchResponse.await().items.size}")
        searchResponse.await().items.forEach {
            Log.d(TAG, "creating YoutubeVideo instances... size:${youtubeSearchResult.size}")
            Log.d(TAG, it.toString())

            youtubeSearchResult.add(
                YoutubeVideo.createYouTubeVideo(
                    category = "GGugGGug",
                    youtubeSnippet = it.snippet
                )
            )
//            Log.d(TAG, "creating YoutubeVideo instances... size:${it.snippet.title}")
        }
        val youtubeShorts = mutableListOf<YoutubeVideo>()
        val youtubeVideo = mutableListOf<YoutubeVideo>()
        youtubeSearchResult.forEach{
            if (it.isShorts)
                youtubeShorts.add(it)
            else youtubeVideo.add(it)
        }

        shortsadapter.items.addAll(youtubeShorts)
        videoadapter.items.addAll(youtubeVideo)
        shortsadapter.notifyDataSetChanged()
        videoadapter.notifyDataSetChanged()

        Log.d(TAG, "youtubeShorts = ${youtubeShorts.size}")
        Log.d(TAG, "youtubeVideo = ${youtubeVideo.size}")
    }

}