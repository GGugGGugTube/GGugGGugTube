package com.example.myapplication.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CategoryItem
import com.example.myapplication.databinding.SearchRecyclerviewItemBinding

class SearchAdapter(val mItem: List<CategoryItem>): RecyclerView.Adapter<SearchAdapter.SearchItemHolder>() {

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemHolder {
        val binding = SearchRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        Log.d("MyAdapter", "onCreateViewHolder()")
        return SearchItemHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchItemHolder, position: Int) {
        Log.d("MyAdapter","onBindViewHolder() position = $position")
        holder.itemView.setOnClickListener{//클릭 이벤트 추가 부분
            itemClick?.onClick(it, position)
        }
        holder.icon.setImageResource(mItem[position].animalIcon)
        holder.Name.text = mItem[position].animalName

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItem.size
    }

    inner class SearchItemHolder(val binding: SearchRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        val icon = binding.ivSearchitem
        val Name = binding.tvSearchitemname
    }
}