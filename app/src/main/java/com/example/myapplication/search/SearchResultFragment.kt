package com.example.myapplication.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Constants
import com.example.myapplication.CtItem
import com.example.myapplication.databinding.FragmentSearchResultBinding
import com.example.myapplication.model.NaverModel
import com.example.myapplication.naverdictionary.NaverData
import com.example.myapplication.naverdictionary.NaverRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var mContext: Context
    private lateinit var dictionaryAdapter: DictionaryAdapter
    private var animalData: CtItem.CategoryItem? = null
    var resItem: ArrayList<NaverModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        initAnimal()
        initDictionary(inflater, container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initAnimal() {
        animalData = arguments?.getParcelable<CtItem>("EXTRA_ANIMAL") as CtItem.CategoryItem?
        binding.tvAnimal?.text = animalData?.animalName

        val animal = binding.tvAnimal.text.toString()
        fenchNaverResult(animal)
    }

    private fun fenchNaverResult(query: String) {
        NaverRetrofit.naverApiService.naverDic(Constants.NAVER_CLIENT_ID, Constants.NAVER_CLIENT_SECRET, query)
            ?.enqueue(object: Callback<NaverData> {
                override fun onResponse(call: Call<NaverData>, response: Response<NaverData>) {
                    val body = response.body()

                    body?.let {
                        response.body()!!.items.forEach{ item ->
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
        with(binding){
            dictionaryAdapter = DictionaryAdapter(mContext)
            searchDictionary.adapter = dictionaryAdapter
            searchDictionary.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            searchDictionary.itemAnimator = null
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(ctItem: CtItem) =
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("EXTRA_ANIMAL", ctItem)
                }
            }
    }
}