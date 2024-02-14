package com.example.myapplication.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.VideoItemBinding
import com.example.myapplication.detail.VideoDetailFragment
import com.example.myapplication.search.SearchResultAdapter
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        shortsView()
        videoView()

        return binding.root
    }

    private fun shortsView() {
        shortsadapter = HomeShortsAdapter(mContext)
        binding.reHomeBestShorts.adapter = shortsadapter
        binding.reHomeBestShorts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun videoView() {
        videoadapter = HomeVideoAdapter(mContext)
        binding.reHomeVideo.adapter = videoadapter
        binding.reHomeVideo.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchYoutubeResult()


        //비디오 클릭시 디테일 화면으로
        videoadapter.homevideoClick = object : HomeVideoAdapter.homeVideoClick {
            override fun onClick(item: YoutubeVideo, position: Int) {
                val detailFragment = VideoDetailFragment.newInstance(item)

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.main_frame, detailFragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }.commit()
            }
        }
        //이하 동문
        shortsadapter.homeshortsclick = object : HomeShortsAdapter.homeShortsClick {
            override fun onClick(item: YoutubeVideo, position: Int) {
                val detailFragment = VideoDetailFragment.newInstance(item)

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.main_frame, detailFragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }.commit()
            }
        }
    }

    private fun fetchYoutubeResult() = lifecycleScope.launch {
        var searchResponse = async {
            YoutubeNetworkClient.youtubeNetWork.getMostPopularPetAndAnimals()
        }

        val youtubeSearchResult = mutableListOf<YoutubeVideo>()
        searchResponse.await().items.forEach {
            Log.d(TAG, it.toString())

            youtubeSearchResult.add(
                YoutubeVideo.createYouTubeVideo("GGugGGug", it)
            )
            Log.d(TAG, "videoId = ${it.id}")
        }

        val youtubeShorts = mutableListOf<YoutubeVideo>()
        val youtubeVideo = mutableListOf<YoutubeVideo>()

        youtubeSearchResult.forEach {
            if (it.isShorts)
                youtubeShorts.add(it)
            else youtubeVideo.add(it)
        }
        Log.d(TAG, "youtubeShorts = ${youtubeShorts.size}")
        Log.d(TAG, "youtubeVideo = ${youtubeVideo.size}")


        binding.animationBingleShorts.visibility = View.GONE
        binding.animationBingleVideo.visibility = View.GONE

        if (youtubeShorts.isEmpty()) {
            binding.tvNoShorts.isVisible = true
        } else {
            binding.reHomeBestShorts.isVisible = true
            shortsadapter.items.addAll(youtubeShorts)
            shortsadapter.notifyDataSetChanged()
        }

        if (youtubeVideo.isEmpty()) {
            binding.tvNoVidoes.isVisible = true
        } else {
            binding.reHomeVideo.isVisible = true
            videoadapter.items.addAll(youtubeVideo)
            videoadapter.notifyDataSetChanged()
        }
    }
}