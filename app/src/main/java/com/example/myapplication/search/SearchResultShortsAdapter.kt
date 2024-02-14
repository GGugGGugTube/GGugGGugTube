package com.example.myapplication.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.ShortsItemBinding
import com.example.myapplication.like.OnHeartClickedListener

class SearchResultShortsAdapter(val mItems: List<YoutubeVideo>) :
    RecyclerView.Adapter<SearchResultShortsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ShortsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class Holder(val binding: ShortsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val thumbnailImageView = binding.ivShortsImage
        private val titleTextView = binding.tvShortsName
        private val timeTextView = binding.tvShortsTime
        private val heartImageView = binding.ivShortsLike

        fun bind(data: YoutubeVideo) {
            data.thumbnail?.let{
                Glide.with(binding.root.context)
                    .load(it)
                    .into(thumbnailImageView)
            }

            titleTextView.text = data.title
            timeTextView.text = data.publishedAt
            setHeartImageView(data.isLiked)

            heartImageView.setOnClickListener {
                data.isLiked = !data.isLiked
                setHeartImageView(data.isLiked)
                OnHeartClickedListener.onHeartClicked(data)
                notifyItemChanged(position)
            }
        }

        private fun setHeartImageView(isLiked: Boolean) {
            heartImageView.setImageResource(
                if (isLiked) R.drawable.icon_foot
                else R.drawable.icon_foot_line
            )
        }
    }
}