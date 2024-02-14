package com.example.myapplication.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.VideoItemBinding
import com.example.myapplication.like.OnHeartClickedListener

class SearchResultAdapter(val mItems: List<YoutubeVideo>) :
    RecyclerView.Adapter<SearchResultAdapter.Holder>() {

    private val onHeartClickedListener = OnHeartClickedListener()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = mItems[position]

        holder.heartImageView.setOnClickListener {
            data.isLiked = !data.isLiked
            holder.setHeartImageView(data.isLiked)
            onHeartClickedListener.onHeartClicked(data)
        }
        holder.bind(data)
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
        val heartImageView = binding.ivVideoLike

        fun bind(data: YoutubeVideo) {
            data.thumbnail?.let {
                Glide.with(binding.root.context)
                    .load(it)
                    .into(thumbnailImageView)
            }

            titleTextView.text = data.title
            timeTextView.text = data.publishedAt
            setHeartImageView(data.isLiked)
        }

        fun setHeartImageView(isLiked: Boolean) {
            heartImageView.setImageResource(
                if (isLiked) R.drawable.icon_foot
                else R.drawable.icon_foot_line
            )
        }
    }
}