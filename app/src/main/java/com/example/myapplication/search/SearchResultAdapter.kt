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

    interface VideoClick {
        fun onClick(item: YoutubeVideo, position: Int)
    }

    var videoClick: VideoClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            videoClick?.onClick(mItems[position], position)
        }

        holder.bind(position)
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

        fun bind(position:Int) {
            val data = mItems[position]

            data.thumbnail?.let {
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