package com.example.myapplication.myvideo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import com.example.myapplication.YouTubeViewType
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.LongScaleShortsItemBinding
import com.example.myapplication.databinding.SmallVideoItemBinding
import com.example.myapplication.like.OnHeartClickedListener
import com.example.myapplication.watchlist.OnWatchListener

class MyVideoAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<YoutubeVideo>()
    private var shortsItems = mutableListOf<YoutubeVideo>()
    override fun getItemViewType(position: Int): Int {
        return if (position < items.size && items[position].isShorts) {
            YouTubeViewType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
        } else YouTubeViewType.VIEW_TYPE_SMALL_VIDEO.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            YouTubeViewType.VIEW_TYPE_SMALL_VIDEO.ordinal -> {
                val binding = SmallVideoItemBinding.inflate(inflater, parent, false)
                SmallVideoViewHolder(binding)
            }

            YouTubeViewType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal -> {
                val binding = LongScaleShortsItemBinding.inflate(inflater, parent, false)
                LongScaleShortsViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SmallVideoViewHolder -> {
                if (position < items.size) { // 리스트의 범위를 벗어나지 않도록 인덱스 확인
                    holder.bind(items[position])
                }
            }
            is LongScaleShortsViewHolder -> {
                if (position < shortsItems.size) { // 리스트의 범위를 벗어나지 않도록 인덱스 확인
                    holder.bind(shortsItems[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + shortsItems.size
    }

    inner class SmallVideoViewHolder(private val binding: SmallVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YoutubeVideo) {
            // 썸네일 이미지 로드
            Glide.with(MyApplication.appContext!!)
                .load(item.thumbnail)
                .into(binding.ivSmallVideoImage)

            // 비디오 제목 설정
            binding.tvSmallVideoName.text = item.title

            // 비디오 시간 설정
            binding.tvSmallVideoTime.text = getDateFromTimestampWithFormat(
                item.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )

            binding.ivSmallVideoLike.setOnClickListener {
                item.isLiked = !item.isLiked
                setHeartImageView(item.isLiked)
                OnHeartClickedListener.onHeartClicked(item)
                OnWatchListener.onWatch(item)
                notifyItemRemoved(position)

            }
        }

        private fun setHeartImageView(liked: Boolean) {
            binding.ivSmallVideoLike.setImageResource(
                if (liked) R.drawable.icon_foot
                else R.drawable.icon_foot_line
            )
        }
    }

    inner class LongScaleShortsViewHolder(private val binding: LongScaleShortsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YoutubeVideo) {
            Glide.with(MyApplication.appContext!!)
                .load(item.thumbnail)
                .into(binding.ivLsShortsImage)

            // 비디오 제목 설정
            binding.tvLsShortsVideoName.text = item.title

            // 비디오 시간 설정
            binding.tvLsShortsVideoTime.text = getDateFromTimestampWithFormat(
                item.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )

            // 좋아요 버튼 관련 로직 추가
            binding.ivLsShortsLike.setOnClickListener {
                item.isLiked = !item.isLiked
                setHeartImageView(item.isLiked)
                OnHeartClickedListener.onHeartClicked(item)
                OnWatchListener.onWatch(item)
                notifyItemRemoved(position)

            }
        }

        private fun setHeartImageView(liked: Boolean) {
            binding.ivLsShortsLike.setImageResource(
                if (liked) R.drawable.icon_foot
                else R.drawable.icon_foot_line
            )
        }
    }


    fun updateItems(newItems: List<YoutubeVideo>) {
        items.clear() // 기존 아이템 리스트 초기화
        items.addAll(newItems) // 새로운 아이템 리스트 추가
        notifyDataSetChanged() // 변경 사항을 RecyclerView에 알림
    }
    fun updateShortsItems(newShortsItems: List<YoutubeVideo>) {
        shortsItems.clear() // 기존 쇼츠 시청 목록 초기화
        shortsItems.addAll(newShortsItems) // 새로운 쇼츠 시청 목록 추가
        notifyDataSetChanged() // 변경 사항을 RecyclerView에 알림
    }

}


