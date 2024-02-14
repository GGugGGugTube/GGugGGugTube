package com.example.myapplication.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.CategoryItemManager.Companion.getItem
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.youtubeApi.YoutubeNetWorkInterface
import com.example.myapplication.youtubeApi.YoutubeNetworkClient
import kotlinx.coroutines.launch


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

        binding.tvEdit.setOnClickListener {
            adapter.animalClick = object : SearchAdapter.AnimalClick {
                override fun onClick(view: View, position: Int) {
//                    val ad = AlertDialog.Builder(context)
//                    ad.setTitle("삭제")
//                    ad.setMessage("정말 삭제하시겠습니까?")
//                    ad.setPositiveButton("확인") { dialog, _ ->
//                        CtItem.CategoryItem.removeAt(position)
//                        adapter.notifyItemRemoved(position)
//                    }
//                    ad.setNegativeButton("취소"){dialog, _->
//                        dialog.dismiss()
//                        }
                }
            }
        }

        adapter.plusClick = object : SearchAdapter.PlusClick {
            override fun onClick(view: View, position: Int) {

            }
        }
        return binding.root
    }

    private fun itemView() {
        adapter = SearchAdapter(getItem())
        binding.reSearch.adapter = adapter
        gridManager = GridLayoutManager(context, 3)
        binding.reSearch.layoutManager = gridManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            YoutubeNetworkClient.youtubeNetWork.getSearchedPetAndAnimals("개")
        }
    }


}