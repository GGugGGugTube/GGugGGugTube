package com.example.myapplication.search

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.CtItem
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.databinding.SearchRecyclerviewItemBinding



class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var _binding: SearchRecyclerviewItemBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var gridManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        _binding = SearchRecyclerviewItemBinding.inflate(layoutInflater, container, false)

        itemView()

        //삭제 다이얼로그
        adapter.itemLongClick = object : SearchAdapter.ItemLongClick {
            override fun onLongClick(id: Int, position: Int) {
                val ad = AlertDialog.Builder(context)
                ad.setTitle("삭제")
                ad.setMessage("정말로 삭제하시겠습니까?")
                ad.setPositiveButton("확인"){dialog,_ ->
                    CategoryItemManager.removeItem(id)
                    adapter.changeDataset(CategoryItemManager.getItem())
                }
                ad.setNegativeButton("취소"){dialog,_ ->
                    dialog.dismiss()
                }
                ad.show()
            }
        }


        adapter.plusClick = object : SearchAdapter.PlusClick{
            override fun onClick(view: View, position: Int) {
                dialog()
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            adapter.animalClick = object : SearchAdapter.AnimalClick {
                override fun onClick(item: CtItem, position: Int) {
                    val resultFragment = SearchResultFragment.newInstance(item)

                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.main_frame, resultFragment)
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }.commit()
                }
            }
        }
    }

    private fun itemView() {
        adapter = SearchAdapter(CategoryItemManager.getItem())
        binding.reSearch.adapter = adapter
        gridManager = GridLayoutManager(context, 3)
        binding.reSearch.layoutManager = gridManager
    }

    //추가 버튼을 누르면 뜨는 다이얼로그
    private fun dialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("동물 추가")

        val v1 = layoutInflater.inflate(R.layout.searchdialog, null)
        builder.setView(v1)

        val listener = DialogInterface.OnClickListener { p0, p1 ->
            val alert = p0 as AlertDialog
            val edit: EditText? = alert.findViewById(R.id.et_searchdialog)

            _binding.tvSearchitemname.text = edit?.text
            CategoryItemManager.addItem(edit?.text.toString())
            adapter.changeDataset(CategoryItemManager.getItem())
            adapter.notifyDataSetChanged()
        }

        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)

        builder.show()
    }
}