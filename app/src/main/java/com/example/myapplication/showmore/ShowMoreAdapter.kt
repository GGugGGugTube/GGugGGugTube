package com.example.myapplication.showmore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.SmallVideoItemBinding

class ShowMoreAdapter(private val mContext: Context) :
    RecyclerView.Adapter<ShowMoreAdapter.SmallVideoViewHolder>() {

    private var items = mutableListOf<YoutubeVideo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMoreAdapter.SmallVideoViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = SmallVideoItemBinding.inflate(inflater, parent, false)
        return SmallVideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SmallVideoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SmallVideoViewHolder(private val binding: SmallVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YoutubeVideo) {
            // 기존의 MyVideoAdapter의 내용과 같이 데이터를 바인딩합니다.
            Glide.with(mContext)
                .load(item.thumbnail)
                .into(binding.ivSmallVideoImage)

            binding.ivSmallVideoImage.scaleType =
                if (item.isShorts) ImageView.ScaleType.CENTER_INSIDE
                else ImageView.ScaleType.CENTER_CROP

            binding.tvSmallVideoName.text = item.title
            binding.tvSmallVideoTime.text = getDateFromTimestampWithFormat(
                item.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )

            binding.ivSmallVideoLike.setOnClickListener {
                //TODO 좋아요 삭제
            }
        }
    }

    fun updateItems(newItems: List<YoutubeVideo>) {
        items.clear() // 기존 아이템 리스트 초기화
        items.addAll(newItems) // 새로운 아이템 리스트 추가
        notifyDataSetChanged() // 변경 사항을 RecyclerView에 알림
    }
}