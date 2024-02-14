package com.example.myapplication.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.bumptech.glide.Glide
import com.example.myapplication.CtItem
import com.example.myapplication.search.SearchAdapter
import com.example.myapplication.databinding.VideoItemBinding
import com.example.myapplication.like.OnHeartClickedListener

class HomeVideoAdapter(private val mContext: Context) :
    RecyclerView.Adapter<HomeVideoAdapter.ItemViewHolder>() {

class HomeVideoAdapter(val mContext: Context): RecyclerView.Adapter<HomeVideoAdapter.ItemViewHolder>() {

    var items = ArrayList<YoutubeVideo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeVideoAdapter.ItemViewHolder {
    interface onLikedClick{
        fun onClick(item: CtItem, position: Int)
    }

    var likedClick : HomeVideoAdapter.onLikedClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): HomeVideoAdapter.ItemViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeVideoAdapter.ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var iv_thum_video: ImageView = binding.ivVideoImage
        var iv_video_like: ImageView = binding.ivVideoLike
        var iv_video_name: TextView = binding.tvVideoName
        var iv_video_time: TextView = binding.tvVideoTime

        fun bind(position: Int) {
            val currentItem = items[position]

        // 썸네일 이미지 로드
        Glide.with(mContext)
            .load(currentItem.thumbnail)
            .into(holder.iv_thum_video)
        //동영상 이름
        holder.iv_video_name.text = currentItem.title
        //동영상 시간
        holder.iv_video_time.text = currentItem.publishedAt
    }
            // 썸네일 이미지 로드
            Glide.with(mContext)
                .load(currentItem.thumbnail)
                .into(iv_thum_video)
            //동영상 이름
            iv_video_name.text = currentItem.title

            //동영상 시간
            iv_video_time.text = DateUtils.getDateFromTimestampWithFormat(
                currentItem.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )
            setHeartImageView(currentItem.isLiked)

            iv_video_like.setOnClickListener {
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