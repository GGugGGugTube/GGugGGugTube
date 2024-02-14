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
import com.example.myapplication.databinding.FragmentSearchResultBinding
import com.example.myapplication.model.NaverModel
import com.example.myapplication.naverdictionary.NaverData
import com.example.myapplication.naverdictionary.NaverRetrofit
import com.example.myapplication.youtubeApi.YoutubeNetworkClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException


class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var mContext: Context

    private lateinit var dictionaryAdapter: DictionaryAdapter

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
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        initAnimal()
        initDictionary(inflater, container)
        initViewPagerButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackButton()

        val YoutubeVideoSearchResult = fetchYoutubeResult(animalData.animalName)
    }

    private fun initAnimal() {
        animalData.animalName.run{
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
    private fun initBackButton(){
        binding.imgSearchBack.setOnClickListener {
            endSearchResultFragment()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            endSearchResultFragment()
        }
    }

    private fun endSearchResultFragment(){
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun fetchYoutubeResult(query: String) = lifecycleScope.launch {
        //TODO 유튜브 API 검색 결과 받아오기
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