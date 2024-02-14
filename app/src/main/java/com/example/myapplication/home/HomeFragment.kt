package com.example.myapplication.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.youtubeApi.YoutubeNetWorkInterface
import com.example.myapplication.youtubeApi.YoutubeNetworkClient
import kotlinx.coroutines.launch

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
    }

    private fun videoView(){
        videoadapter = HomeVideoAdapter(mContext)
        binding.reHomeVideo.adapter = videoadapter
        binding.reHomeVideo.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            YoutubeNetworkClient.youtubeNetWork.getMostPopularPetAndAnimals()
        }
    }

}