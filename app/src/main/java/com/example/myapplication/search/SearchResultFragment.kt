package com.example.myapplication.search

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Constants
import com.example.myapplication.CtItem
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.FragmentSearchResultBinding
import com.example.myapplication.detail.VideoDetailFragment
import com.example.myapplication.like.LikedConstants
import com.example.myapplication.model.NaverModel
import com.example.myapplication.naverdictionary.NaverData
import com.example.myapplication.naverdictionary.NaverRetrofit
import com.example.myapplication.youtubeApi.YoutubeNetworkClient
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchResultFragment : Fragment() {
    private val TAG = "SearchResultFragment"

    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var mContext: Context

    private lateinit var dictionaryAdapter: DictionaryAdapter
    private lateinit var shortsAdapter: SearchResultShortsAdapter
    private lateinit var videoAdapter: SearchResultAdapter

    private val animalData: CtItem.CategoryItem
        get() = requireArguments().getParcelable<CtItem>(ARG_ANIMAL) as CtItem.CategoryItem
            ?: throw IllegalArgumentException("Argument %{ARG_ANIMAL} required")


    var resItem: ArrayList<NaverModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        initAnimal()
        initDictionary(inflater, container)
        initViewPagerButton()

        //Bottom Navigation 숨기기
        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNavigation(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        initBackButton()
        fetchYoutubeResult(animalData.animalName)
        initUpButton()
    }


    // 동물 카테고리를 클릭하면 그 동물의 이름 가져다오기
    private fun initAnimal() {
        animalData.animalName.run {
            binding.tvAnimal.text = this
            fetchNaverResult(this)
        }
    }

    private fun fetchNaverResult(query: String) {
        NaverRetrofit.naverApiService.naverDic(
            Constants.NAVER_CLIENT_ID,
            Constants.NAVER_CLIENT_SECRET,
            query
        )?.enqueue(object : Callback<NaverData> {
            override fun onResponse(
                call: Call<NaverData>,
                response: Response<NaverData>
            ) {
                val body = response.body()

                body?.let {
                    response.body()!!.items.forEach { item ->
                        val title = item.title
                        val description = item.description
                        val url = item.thumbnail
                        resItem.add(NaverModel(title, description, url))
                    }
                }

                dictionaryAdapter.items = resItem
                dictionaryAdapter.notifyDataSetChanged()
                Log.d("네이버api 검사", "응답")
            }


            override fun onFailure(call: Call<NaverData>, t: Throwable) {
                Log.d("네이버api 검사", "응답실패")
            }
        })
    }

    private fun initDictionary(inflater: LayoutInflater, container: ViewGroup?) {
        with(binding) {
            dictionaryAdapter = DictionaryAdapter(mContext)
            searchDictionary.adapter = dictionaryAdapter
            searchDictionary.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    // ViewPager2 양 옆의 화살표 작동시키기
    private fun initViewPagerButton() {
        with(binding) {
            imgArrowBack.setOnClickListener {
                val current = searchDictionary.currentItem

                searchDictionary.setCurrentItem(current - 1, true)
                if (current == 0) {
                    binding.searchDictionary.setCurrentItem(
                        dictionaryAdapter.itemCount - 1,
                        true
                    )
                }
            }

            imgArrowForword.setOnClickListener {
                val current = searchDictionary.currentItem

                searchDictionary.setCurrentItem(current + 1, true)
                if (current == dictionaryAdapter.itemCount - 1) {
                    binding.searchDictionary.setCurrentItem(0, true)
                }
            }
        }
    }

    private fun initBackButton() {
        binding.imgSearchBack.setOnClickListener {
            endSearchResultFragment()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            endSearchResultFragment()
        }
    }

    private fun endSearchResultFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNavigation(false)
    }

    private fun fetchYoutubeResult(query: String) = lifecycleScope.launch {
        var searchResponse = async {
            YoutubeNetworkClient.youtubeNetWork.getSearchedPetAndAnimals(query)
        }

        val youtubeSearchResult = mutableListOf<YoutubeVideo>()
        Log.d(TAG, "result size: ${searchResponse.await().items.size}")
        searchResponse.await().items.forEach {
            Log.d(TAG, it.toString())

            youtubeSearchResult.add(
                YoutubeVideo.createYouTubeVideo(animalData.Id, it)
            )
        }

        binding.animationBingleShorts.visibility = View.GONE
        binding.animationBingleVideo.visibility = View.GONE

        val shorts = youtubeSearchResult.filter { it.isShorts }
        Log.d(TAG, "shorts size: ${shorts.size}")
        if (shorts.isEmpty()) {
            binding.tvNoShorts.isVisible = true
        } else {
            initShortsRecyclerView(shorts)
            binding.reSearchShorts.isVisible = true
        }

        val videos = youtubeSearchResult.filter { !it.isShorts }
        Log.d(TAG, "videos size: ${videos.size}")
        if (videos.isEmpty()) {
            binding.tvNoVideo.isVisible = true
        } else {
            initVideoRecyclerView(videos)
            binding.reSearchVideo.isVisible = true
        }
    }

    private fun initShortsRecyclerView(shorts: List<YoutubeVideo>) {
        shortsAdapter = SearchResultShortsAdapter(shorts)
        binding.reSearchShorts.adapter = shortsAdapter
    }

    private fun initVideoRecyclerView(videos: List<YoutubeVideo>) {
        videoAdapter = SearchResultAdapter(videos)
        binding.reSearchVideo.adapter = videoAdapter

        videoAdapter.videoClick = object : SearchResultAdapter.VideoClick {
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
    private fun initUpButton(){
        binding.reSearchVideo.setOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    Handler().postDelayed(Runnable {
                        binding.fabUp.isVisible = false
                    }, 2500)
                }
                else binding.fabUp.isVisible = true
            }
        })

        binding.fabUp.setOnClickListener {
            binding.nestedScrollView.smoothScrollTo(0,0)
        }
    }

    companion object {
        private const val ARG_ANIMAL = "argAnimal"

        @JvmStatic
        fun newInstance(ctItem: CtItem) =
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ANIMAL, ctItem)
                }
            }
    }
}