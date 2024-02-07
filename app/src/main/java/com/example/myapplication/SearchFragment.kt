package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var gridManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        itemView()

        return binding.root
    }
    private fun itemView() {
        val categoryItem = CategoryItemManager.getItem().sortedBy { it.Id }
        adapter = SearchAdapter(categoryItem)
        binding.reSearch.adapter = adapter
        gridManager = GridLayoutManager(context, 3)
        binding.reSearch.layoutManager = gridManager
    }

}