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
    private lateinit var binding: FragmentMyVideoBinding

    private val myLikeAdapter = MyLikedVideoAdapter()
    private val myWatchAdapter = MyWatchedVideoAdapter()
    private val myShortsWatchAdapter = MyWatchedVideoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        myLikeAdapter.onHeartClickedInMyLikedListeners = listOf(
            myWatchAdapter, myShortsWatchAdapter
        )
        binding.reMyLikeList.adapter = myLikeAdapter
    }

    private fun initWatchRecyclerView() {
        myWatchAdapter.onHeartClickedInMyWatchedListener = myLikeAdapter
        binding.reMyWatchList.adapter = myWatchAdapter

        val myWatchItemList = WatchListUtils.getVideoWatchList()
        myWatchAdapter.updateItems(myWatchItemList)
    }

    private fun initShortsWatchRecyclerView() {
        myShortsWatchAdapter.onHeartClickedInMyWatchedListener = myLikeAdapter
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





