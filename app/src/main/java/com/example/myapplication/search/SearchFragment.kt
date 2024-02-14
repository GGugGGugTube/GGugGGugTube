package com.example.myapplication.search


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.CategoryItemManager
import com.example.myapplication.CategoryItemManager.Companion.addItem
import com.example.myapplication.CategoryItemManager.Companion.getItem
import com.example.myapplication.CategoryItemManager.Companion.getPlus
import com.example.myapplication.CtItem
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.databinding.SearchRecyclerviewItemBinding


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var _binding: SearchRecyclerviewItemBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var gridManager: GridLayoutManager
    private var dataList = ArrayList<CtItem>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        _binding = SearchRecyclerviewItemBinding.inflate(layoutInflater, container, false)

        itemView()

        adapter.itemLongClick = object : SearchAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                val ad = AlertDialog.Builder(context)
                ad.setTitle("삭제")
                ad.setMessage("정말로 삭제하시겠습니까?")
                ad.setPositiveButton("확인"){dialog,_ ->
                    dataList.removeAt(position)
                    adapter.notifyItemRemoved(position)
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

    private fun itemView() {

        dataList.addAll(getItem())
        adapter = SearchAdapter(dataList)
        binding.reSearch.adapter = adapter
        gridManager = GridLayoutManager(context, 3)
        binding.reSearch.layoutManager = gridManager


    }

    //
    private fun dialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("동물 추가")

        val v1 = layoutInflater.inflate(R.layout.searchdialog, null)
        builder.setView(v1)

        val listener = DialogInterface.OnClickListener {p0, p1 ->
            val alert = p0 as AlertDialog
            val edit: EditText? = alert.findViewById(R.id.et_searchdialog)

            _binding.tvSearchitemname.text = edit?.text
            CategoryItemManager.addItem(edit?.text.toString())
//            adapter.notifyItemRemoved(getPlus().size)
            adapter.changeDataset(CategoryItemManager.getItem())
            adapter.notifyDataSetChanged()
        }

        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)

        builder.show()
    }
}