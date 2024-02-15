package com.example.myapplication.showmore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.addCallback
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.databinding.FragmentShowMoreBinding
import com.example.myapplication.search.CategoryItemManager


class ShowMoreFragment : Fragment() {
    private lateinit var binding: FragmentShowMoreBinding
    private lateinit var showMoreAdapter:ShowMoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 프래그먼트의 바인딩 초기화
        binding = FragmentShowMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initShowMoreRecyclerView()
        initBackButton()
    }

    private fun initShowMoreRecyclerView(){
        showMoreAdapter = ShowMoreAdapter(CategoryItemManager.getCategoryItems())
        binding.reShowMore.adapter = showMoreAdapter
        binding.reShowMore.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
    }

    private fun initBackButton(){
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            parentFragmentManager.popBackStack()
        }
    }
}