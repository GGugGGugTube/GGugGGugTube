package com.example.myapplication.myvideo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.FragmentMyVideoBinding

class MyVideoFragment : Fragment() {
    private lateinit var binding: FragmentMyVideoBinding // MyVideoFragment의 바인딩 객체
    private lateinit var adapter: MyVideoAdapter
    private lateinit var myLikeAdapter: MyVideoAdapter
    private lateinit var myWatchAdapter: MyVideoAdapter
    private lateinit var myShortsWatchAdapter: MyVideoAdapter

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

        // 어댑터 초기화
        adapter = MyVideoAdapter(requireContext())
        myLikeAdapter = MyVideoAdapter(requireContext())
        myWatchAdapter = MyVideoAdapter(requireContext())
        myShortsWatchAdapter = MyVideoAdapter(requireContext())

        // 리사이클러뷰에 레이아웃 매니저 설정
        binding.reMyLikeList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMyWatchList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMyShortsWatchList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 리사이클러뷰에 어댑터 설정
        binding.reMyLikeList.adapter = myLikeAdapter
        binding.reMyWatchList.adapter = myWatchAdapter // reMyWatchList에도 어댑터 설정
        binding.reMyShortsWatchList.adapter = myShortsWatchAdapter // reMyShortsWatchList에도 어댑터 설정

        // 아이템 리스트 가져오기
        val itemList = getItemList()
        val myLikeItemList = getMyLikeItemList()
        val myWatchItemList = getMyWatchItemList()
        val myShortsWatchItemList = getMyShortsWatchItemList()

        // 어댑터에 아이템 리스트 설정
        adapter.updateItems(itemList)
        myLikeAdapter.updateItems(myLikeItemList)
        myWatchAdapter.updateItems(myWatchItemList)
        myShortsWatchAdapter.updateItems(myShortsWatchItemList)
    }

    private fun getItemList(): List<YoutubeVideo> {
        // 리사이클러뷰에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getMyLikeItemList(): List<YoutubeVideo> {
        // MyLikeList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getMyWatchItemList(): List<YoutubeVideo> {
        // MyWatchList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getMyShortsWatchItemList(): List<YoutubeVideo> {
        // MyShortsWatchList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }
}



