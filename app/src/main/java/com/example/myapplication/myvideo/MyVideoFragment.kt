package com.example.myapplication.myvideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyVideoBinding
import com.example.myapplication.like.LikedUtils
import com.example.myapplication.showmore.ShowMoreFragment
import com.example.myapplication.watchlist.OnWatchListener
import com.example.myapplication.watchlist.WatchListUtils

class MyVideoFragment : Fragment() {
    private lateinit var binding: FragmentMyVideoBinding // MyVideoFragment의 바인딩 객체

    private lateinit var myLikeAdapter: MyLikedVideoAdapter
    private lateinit var myWatchAdapter: MyWatchedVideoAdapter
    private lateinit var myShortsWatchAdapter: MyWatchedVideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 프래그먼트의 바인딩 초기화
        binding = FragmentMyVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLikedRecyclerView()
        initWatchRecyclerView()
        initShortsWatchRecyclerView()

        initShowMoreButton()
        initWatchClearButton()
        initShortsWatchClearButton()
    }

    private fun initLikedRecyclerView() {
        myLikeAdapter = MyLikedVideoAdapter()
        binding.reMyLikeList.adapter = myLikeAdapter

        val myLikeItemList = LikedUtils.getLikedVideos()
        myLikeAdapter.updateItems(myLikeItemList)
    }

    private fun initWatchRecyclerView() {
        myWatchAdapter = MyWatchedVideoAdapter()
        binding.reMyWatchList.adapter = myWatchAdapter
        val myWatchItemList = WatchListUtils.getVideoWatchList()
        myWatchAdapter.updateItems(myWatchItemList)
    }

    private fun initShortsWatchRecyclerView() {
        myShortsWatchAdapter = MyWatchedVideoAdapter()
        binding.reMyShortsWatchList.adapter = myShortsWatchAdapter

        val myShortsWatchItemList = WatchListUtils.getShortsWatchList()
        myShortsWatchAdapter.updateItems(myShortsWatchItemList)
    }

    private fun initShowMoreButton() {
        binding.tvMoreVideo.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, ShowMoreFragment())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initWatchClearButton() {
        binding.tvAllDelete.setOnClickListener {
            OnWatchListener.onWatchClearVideo()
            myWatchAdapter.updateItems(emptyList())
        }
    }

    private fun initShortsWatchClearButton() {
        binding.tvAllDelete2.setOnClickListener {
            OnWatchListener.onWatchClearShorts()
            myShortsWatchAdapter.updateItems(emptyList())
        }
    }

}





