package com.example.myapplication.showmore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat
import com.example.myapplication.YouTubeViewType
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.SmallVideoItemBinding

class ShowMoreAdapter(private val mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<YoutubeVideo>()

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isShorts) {
            YouTubeViewType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
        } else YouTubeViewType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        return when (viewType) {
            YouTubeViewType.VIEW_TYPE_SMALL_VIDEO.ordinal -> {
                val binding = SmallVideoItemBinding.inflate(inflater, parent, false)
                SmallVideoViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items.isEmpty()) {
            // 데이터가 없는 경우의 처리
            // 예를 들어, 빈 상태를 나타내는 뷰를 보여줄 수 있음
        } else {
            when (holder) {
                is SmallVideoViewHolder -> holder.bind(items[position])
            }
        }
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

            binding.tvSmallVideoName.text = item.title
            binding.tvSmallVideoTime.text = getDateFromTimestampWithFormat(
                item.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )

            binding.ivSmallVideoLike.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = items[position]
                    currentItem.isLiked = !currentItem.isLiked // 좋아요 상태 변경
                    notifyItemChanged(position) // 변경 사항을 RecyclerView에 알림
                    // TODO: 좋아요 상태를 저장하는 로직 추가
                }
            }
        }
    }

    fun updateItems(newItems: List<YoutubeVideo>) {
        items.clear() // 기존 아이템 리스트 초기화
        items.addAll(newItems) // 새로운 아이템 리스트 추가
        notifyDataSetChanged() // 변경 사항을 RecyclerView에 알림
    }
}