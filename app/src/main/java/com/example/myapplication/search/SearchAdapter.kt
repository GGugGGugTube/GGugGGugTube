package com.example.myapplication.search

import android.media.RouteListingPreference.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.CtItem
import com.example.myapplication.databinding.SearchRecyclerviewItemBinding

class SearchAdapter(var mItem: List<CtItem>) : RecyclerView.Adapter<ViewHolder>() {


    companion object {
        private const val VIEW_TYPE_ANIMAL = 1
        private const val VIEW_TYPE_PLUS = 2
    }

    interface ItemLongClick{
        fun onLongClick(id: Int, position: Int)
    }

    interface AnimalClick{
        fun onClick(item: CtItem, position: Int)
    }

    interface PlusClick {
        fun onClick(view: View, position: Int)
    }


    var itemLongClick: ItemLongClick? = null
    var animalClick : AnimalClick? = null
    var plusClick : PlusClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_ANIMAL -> {
                val binding = SearchRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AnimalViewHolder(binding)
            }

            else -> {
                val binding = SearchRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PlusViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (val item = mItem[position]) {
            is CtItem.CategoryItem -> {
                (holder as AnimalViewHolder).animalicon.setImageResource(item.animalIcon)
                holder.animalName.text = item.animalName

                holder.itemView.setOnClickListener{//클릭 이벤트 추가 부분
                    animalClick?.onClick(item, position)
                }
                holder.itemView.setOnLongClickListener() OnLongClickListener@{
                    itemLongClick?.onLongClick(item.Id, position)
                    return@OnLongClickListener true
                }
            }

            is CtItem.CategoryPlus -> {
                (holder as PlusViewHolder).plusicon.setImageResource(item.PlusIcon)
                holder.plusName.text = item.PlusName

                holder.itemView.setOnClickListener {//클릭 이벤트 추가 부분
                    plusClick?.onClick(it, position)
                }
            }
        }


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItem.size
    }

    fun changeDataset(newDataSet: List<CtItem>) {
        mItem = newDataSet
        notifyDataSetChanged()
    }



    override fun getItemViewType(position: Int): Int {
        return when (mItem[position]) {
            is CtItem.CategoryItem -> VIEW_TYPE_ANIMAL
            is CtItem.CategoryPlus -> VIEW_TYPE_PLUS
        }
    }

    inner class AnimalViewHolder(val binding: SearchRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val animalicon = binding.ivSearchitem
        val animalName = binding.tvSearchitemname

    }

    inner class PlusViewHolder(val binding: SearchRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val plusicon = binding.ivSearchitem
        val plusName = binding.tvSearchitemname
    }
}