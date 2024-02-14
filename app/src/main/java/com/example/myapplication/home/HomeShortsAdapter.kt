package com.example.myapplication.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.ShortsItemBinding
import com.bumptech.glide.Glide
import com.example.myapplication.CtItem

class HomeShortsAdapter(private val mContext: Context): RecyclerView.Adapter<HomeShortsAdapter.ItemViewHolder>() {

    var items = ArrayList<YoutubeVideo>()

    interface onLikedClick{
        fun onClick(item: CtItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): HomeShortsAdapter.ItemViewHolder {
        val binding = ShortsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeShortsAdapter.ItemViewHolder, position: Int) {
        val currentItem = items[position]

        // 썸네일 이미지 로드
        Glide.with(mContext)
            .load(currentItem.thumbnail)
            .into(holder.iv_thum_shorts)
        //동영상 이름
        holder.iv_shorts_name.text = currentItem.title

        //동영상 시간
        holder.iv_shorts_time.text = currentItem.publishedAt
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: ShortsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var iv_thum_shorts: ImageView = binding.ivShortsImage
        var iv_shorts_like: ImageView = binding.ivShortsLike
        var iv_shorts_name: TextView = binding.tvShortsName
        var iv_shorts_time: TextView = binding.tvShortsTime

        init {
            iv_thum_shorts.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]
        }
    }

    //좋아요 기능 구현ㅜ,ㅜ

}