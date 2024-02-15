package com.example.myapplication.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.VideoItemBinding
import com.example.myapplication.like.OnHeartClickedListener

class HomeVideoAdapter(private val mContext: Context) :
    RecyclerView.Adapter<HomeVideoAdapter.ItemViewHolder>() {

    var items = ArrayList<YoutubeVideo>()

    interface homeVideoClick {
        fun onClick(item: YoutubeVideo, position: Int)
    }

    var homevideoClick: homeVideoClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): HomeVideoAdapter.ItemViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeVideoAdapter.ItemViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener{
            homevideoClick?.onClick(items[position], position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var iv_thum_video: ImageView = binding.ivVideoImage
        var iv_video_like: ImageView = binding.ivVideoLike
        var iv_video_name: TextView = binding.tvVideoName
        var iv_video_time: TextView = binding.tvVideoTime

        fun bind(position: Int) {
            val currentItem = items[position]

            // 썸네일 이미지 로드
            Glide.with(mContext)
                .load(currentItem.thumbnail)
                .into(iv_thum_video)
            //동영상 이름
            iv_video_name.text = currentItem.title

            //동영상 시간
            iv_video_time.text = currentItem.publishedAt

            setHeartImageView(currentItem.isLiked)

            iv_video_like.setOnClickListener{
                currentItem.isLiked = !currentItem.isLiked
                setHeartImageView(currentItem.isLiked)
                OnHeartClickedListener.onHeartClicked(currentItem)
                notifyItemChanged(position)
            }
        }

        private fun setHeartImageView(isLiked: Boolean) {
            iv_video_like.setImageResource(
                if (isLiked) R.drawable.icon_foot
                else R.drawable.icon_foot_line
            )
        }
    }
}