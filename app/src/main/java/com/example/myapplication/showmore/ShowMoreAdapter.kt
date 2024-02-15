package com.example.myapplication.showmore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.CtItem
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.ShowMoreItemBinding
import com.example.myapplication.databinding.SmallVideoItemBinding
import com.example.myapplication.like.LikedUtils

class ShowMoreAdapter(private val categoryItems:List<CtItem.CategoryItem>) :
    RecyclerView.Adapter<ShowMoreAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMoreAdapter.Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShowMoreItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoryItems[position])
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    inner class Holder(private val binding: ShowMoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val animalNameTextView = binding.tvAnimalName
        val animalLikedRecyclerView = binding.reAnimalLiked

        fun bind(category:CtItem.CategoryItem) {
            animalNameTextView.text = category.animalName
            animalLikedRecyclerView.adapter = LikedAdapter(LikedUtils.getAnimalLikedVideos(category))
        }
    }
}