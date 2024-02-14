package com.example.myapplication.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.VideoItemBinding

class SearchResultAdapter(val mItems: List<YoutubeVideo>) :
    RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val thumbnailImageView = binding.ivVideoImage
        private val titleTextView = binding.tvVideoName
        private val timeTextView = binding.tvVideoTime
        private val heartImageView = binding.ivVideoLike

        fun bind(data: YoutubeVideo) {
            data.thumbnail?.let{
                Glide.with(binding.root.context)
                    .load(it)
                    .into(thumbnailImageView)
            }

            titleTextView.text = data.title
            timeTextView.text = data.publishedAt
        }
    }
}