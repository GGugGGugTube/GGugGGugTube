package com.example.myapplication.myvideo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.CtItem
import com.example.myapplication.MyApplication
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.SmallVideoItemBinding
import com.example.myapplication.like.LikedUtils
import com.example.myapplication.like.OnHeartClickedListener
import com.example.myapplication.search.CategoryItemManager

class MyLikedVideoAdapter() :
    RecyclerView.Adapter<MyLikedVideoAdapter.Holder>() {
    private val TAG = "LikedAdapter"

    private var likedItems = LikedUtils.getLikedVideos()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyLikedVideoAdapter.Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SmallVideoItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(likedItems[position])
    }

    override fun getItemCount(): Int {
        return likedItems.size
    }

    fun refreshRecyclerView(){
        likedItems = LikedUtils.getLikedVideos()
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: SmallVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val videoImageView = binding.ivSmallVideoImage
        private val videoNameTextView = binding.tvSmallVideoName
        private val videoTimeTextView = binding.tvSmallVideoTime
        private val heartImageView = binding.ivSmallVideoLike

        fun bind(item: YoutubeVideo) {
            Glide.with(MyApplication.appContext!!)
                .load(item.thumbnail)
                .into(videoImageView)

            videoNameTextView.text = item.title
            videoTimeTextView.text = item.publishedAt
            heartImageView.setOnClickListener {
                item.isLiked = false
                OnHeartClickedListener.onHeartClicked(item)
                refreshRecyclerView()
            }
        }
    }
}