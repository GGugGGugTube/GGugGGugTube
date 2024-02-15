package com.example.myapplication.showmore

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CtItem
import com.example.myapplication.databinding.ShowMoreItemBinding
import com.example.myapplication.like.LikedUtils

class ShowMoreAdapter(private val categoryItems: List<CtItem.CategoryItem>) :
    RecyclerView.Adapter<ShowMoreAdapter.Holder>() {

    private val TAG = "ShowMoreAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMoreAdapter.Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShowMoreItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentCategory = categoryItems[position]

        if (LikedUtils.getAnimalLikedVideos(currentCategory).isEmpty()) {
            holder.itemView. visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        } else {
            holder.itemView.visibility = View.VISIBLE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        holder.bind(categoryItems[position])
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    inner class Holder(private val binding: ShowMoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val animalIconImageView = binding.ivAnimalIcon
        private val animalNameTextView = binding.tvAnimalName
        private val animalLikedRecyclerView = binding.reAnimalLiked

        fun bind(category: CtItem.CategoryItem) {
            animalIconImageView.setImageResource(category.animalIcon)
            animalNameTextView.text = category.animalName
            Log.d(TAG, "animalName: ${category.animalName}")
            animalLikedRecyclerView.adapter = LikedAdapter(category, this@ShowMoreAdapter)
        }
    }
}