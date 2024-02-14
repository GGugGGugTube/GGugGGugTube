package com.example.myapplication.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.youtubeApi.YoutubeNetworkClient.youtubeNetWork
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

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

    private fun shortsView(){
        shortsadapter = HomeShortsAdapter(mContext)
        binding.reHomeBestShorts.adapter = shortsadapter
        binding.reHomeBestShorts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //아이템 깜빡임 방지
        binding.reHomeBestShorts.itemAnimator = null
    }

    private fun videoView(){
        videoadapter = HomeVideoAdapter(mContext)
        binding.reHomeVideo.adapter = videoadapter
        binding.reHomeVideo.layoutManager = LinearLayoutManager(context)
        //아이템 깜빡임 방지
        binding.reHomeVideo.itemAnimator = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            youtubeNetWork.getMostPopularPetAndAnimals()
        }
    }

    private suspend fun getVideoList(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(youtubeNetWork::class.java).also {
            it.getMostPopularPetAndAnimals("snippet", "mostPopular", "ko", 25, "kr", "15")

        }
    }


}