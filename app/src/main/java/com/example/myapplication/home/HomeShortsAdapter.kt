package com.example.myapplication.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.ShortsItemBinding
import com.example.myapplication.like.OnHeartClickedListener

class HomeShortsAdapter(private val mContext: Context) :
    RecyclerView.Adapter<HomeShortsAdapter.ItemViewHolder>() {

    var items = ArrayList<YoutubeVideo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeShortsAdapter.ItemViewHolder {
        val binding = ShortsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeShortsAdapter.ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: ShortsItemBinding) : RecyclerView.ViewHolder(binding.root){
        var iv_thum_shorts: ImageView = binding.ivShortsImage
        var iv_shorts_like: ImageView = binding.ivShortsLike
        var iv_shorts_name: TextView = binding.tvShortsName
        var iv_shorts_time: TextView = binding.tvShortsTime

        fun bind(position: Int) {
            val currentItem = items[position]

            // 썸네일 이미지 로드
            Glide.with(mContext)
                .load(currentItem.thumbnail)
                .into(iv_thum_shorts)
            //동영상 이름
            iv_shorts_name.text = currentItem.title

            //동영상 시간
            iv_shorts_time.text = DateUtils.getDateFromTimestampWithFormat(
                currentItem.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )
            setHeartImageView(currentItem.isLiked)

            iv_shorts_like.setOnClickListener {
                currentItem.isLiked = !currentItem.isLiked
                setHeartImageView(currentItem.isLiked)
                OnHeartClickedListener.onHeartClicked(currentItem)
                notifyItemChanged(position)
            }
        }

        private fun setHeartImageView(isLiked: Boolean) {
            iv_shorts_like.setImageResource(
                if (isLiked) R.drawable.icon_foot
                else R.drawable.icon_foot_line
            )
        }
    }
}