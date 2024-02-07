package com.example.myapplication.myvideo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat

import com.example.myapplication.YouTubeVieType
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.LongScaleShortsItemBinding
import com.example.myapplication.databinding.SmallVideoItemBinding

class MyVideoAdapter(private val mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<YoutubeVideo>()


    override fun getItemViewType(position: Int): Int {


        return if (items[position].isShorts) {
            YouTubeVieType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
        } else  YouTubeVieType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        return when (viewType) {
            YouTubeVieType.VIEW_TYPE_SMALL_VIDEO.ordinal -> {
                val binding = SmallVideoItemBinding.inflate(inflater, parent, false)
                SmallVideoViewHolder(binding)
            }


            YouTubeVieType.VIEW_TYPE_LONG_SCALE_SHORTS.ordinal -> {
                val binding = LongScaleShortsItemBinding.inflate(inflater, parent, false)
                LongScaleShortsViewHolder(binding)
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
                is SmallVideoViewHolder -> holder.bind(items[position - 1])
                is LongScaleShortsViewHolder -> holder.bind(items[position - 1])

                // ViewHolder3의 데이터 처리

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SmallVideoViewHolder(private val binding: SmallVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YoutubeVideo) {
            // 썸네일 이미지 로드
            Glide.with(mContext)
                .load(item.thumbnails)
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
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = items[position - 1]
                    currentItem.isLiked = !currentItem.isLiked // 좋아요 상태 변경
                    notifyItemChanged(position) // 변경 사항을 RecyclerView에 알림
                    // TODO: 좋아요 상태를 저장하는 로직 추가
                }
            }
        }
    }

    inner class LongScaleShortsViewHolder(private val binding: LongScaleShortsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YoutubeVideo) {
            // 기존의 onBindViewHolder 내용과 좋아요 아이콘 관련 로직을 여기에 추가
            // 썸네일 이미지 로드
            Glide.with(mContext)
                .load(item.thumbnails)
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
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = items[position - 1]
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


