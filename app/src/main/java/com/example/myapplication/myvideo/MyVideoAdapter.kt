package com.example.myapplication.myvideo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import com.example.myapplication.YouTubeViewType
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.LongScaleShortsItemBinding
import com.example.myapplication.databinding.SmallVideoItemBinding
import com.example.myapplication.like.LikedUtils
import com.example.myapplication.like.OnHeartClickedListener

class MyVideoAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var currentPosition = RecyclerView.NO_POSITION
    private var items = mutableListOf<YoutubeVideo>()
    override fun getItemViewType(position: Int): Int {
        return if (items[position].isShorts) {
            YouTubeViewType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
        } else YouTubeViewType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
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
            is SmallVideoViewHolder -> holder.bind(items[position])
            is LongScaleShortsViewHolder -> holder.bind(items[position])

            // ViewHolder3의 데이터 처리
        }
    }

    override fun getItemCount(): Int {
        return items.size
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
            binding.tvSmallVideoTime.text = item.publishedAt

            binding.ivSmallVideoLike.setOnClickListener {
                item.isLiked = !item.isLiked
                setHeartImageView(item.isLiked)
                OnHeartClickedListener.onHeartClicked(item)
                notifyItemRemoved(adapterPosition)

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
            binding.tvLsShortsVideoTime.text = item.publishedAt

            // 좋아요 버튼 관련 로직 추가
            binding.ivLsShortsLike.setOnClickListener {
                item.isLiked = false
                OnHeartClickedListener.onHeartClicked(item)
                updateItems(LikedUtils.getLikedVideos())
            }
        }
    }


    fun updateItems(newItems: List<YoutubeVideo>) {
        items.clear() // 기존 아이템 리스트 초기화
        items.addAll(newItems) // 새로운 아이템 리스트 추가
        notifyDataSetChanged() // 변경 사항을 RecyclerView에 알림
    }

}


