package com.example.myapplication.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Constants
import com.example.myapplication.CtItem
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentSearchResultBinding
import com.example.myapplication.model.NaverModel
import com.example.myapplication.naverdictionary.NaverData
import com.example.myapplication.naverdictionary.NaverRetrofit
import com.example.myapplication.youtubeApi.YoutubeNetworkClient
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
        binding.imgSearchBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    // 뒤로가기 누를 시 Bottom Navigation 살리기
    override fun onDestroyView() {
        super.onDestroyView()

        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNavigation(false)
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
        )
            ?.enqueue(object : Callback<NaverData> {
    // 동물의 이름을 query로 받아서 네이버 백과사전 API 응답요청
    private fun fenchNaverResult(query: String) {
        NaverRetrofit.naverApiService.naverDic(Constants.NAVER_CLIENT_ID, Constants.NAVER_CLIENT_SECRET, query)
            ?.enqueue(object: Callback<NaverData> {
                override fun onResponse(call: Call<NaverData>, response: Response<NaverData>) {
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
                    binding.searchDictionary.setCurrentItem(dictionaryAdapter.itemCount - 1, true)
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
    }

    private fun fetchYoutubeResult(query: String) = lifecycleScope.launch {
        Log.d(TAG, "fetching youtube search response...")
        var searchResponse = async {
            YoutubeNetworkClient.youtubeNetWork.getSearchedPetAndAnimals(query)
        }

        val youtubeSearchResult = mutableListOf<YoutubeVideo>()
        Log.d(TAG, "result size: ${searchResponse.await().items.size}")
        searchResponse.await().items.forEach {
            Log.d(TAG, "creating YoutubeVideo instances... size:${youtubeSearchResult.size}")
            Log.d(TAG, it.toString())

            youtubeSearchResult.add(
                YoutubeVideo.createYouTubeVideo(
                    category = animalData.animalName,
                    youtubeSnippet = it.snippet
                )
            )
        }

        Log.d(TAG, "initiate recyclerviews")
        //initShortsRecyclerView() //TODO 숏츠 영상만 필터링하여 넘겨주기
        initVideoRecyclerView(youtubeSearchResult) //TODO 숏츠 아닌 영상만 필터링하여 넘겨주기
    }

    private fun initShortsRecyclerView(shorts: List<YoutubeVideo>) {
        //TODO 숏츠 리싸이클러뷰 설정하기
    }

    private fun initVideoRecyclerView(videos: List<YoutubeVideo>) {
        videoAdapter = SearchResultAdapter(videos)
        binding.reSearchVideo.adapter = videoAdapter
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