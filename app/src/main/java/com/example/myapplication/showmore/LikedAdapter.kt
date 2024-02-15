package com.example.myapplication.showmore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DateUtils.getDateFromTimestampWithFormat
import com.example.myapplication.MyApplication
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.SmallVideoItemBinding

class LikedAdapter(private var likedItems: List<YoutubeVideo>) :
    RecyclerView.Adapter<LikedAdapter.Holder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LikedAdapter.Holder {
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

    inner class Holder(private val binding: SmallVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val videoImageView = binding.ivSmallVideoImage
        val videoNameTextView = binding.tvSmallVideoName
        val videoTimeTextView = binding.tvSmallVideoTime
        val heartImageView = binding.ivSmallVideoLike

        fun bind(item: YoutubeVideo) {
            // 기존의 MyVideoAdapter의 내용과 같이 데이터를 바인딩합니다.
            Glide.with(MyApplication.appContext!!)
                .load(item.thumbnail)
                .into(videoImageView)

            videoNameTextView.text = item.title
            videoTimeTextView.text = getDateFromTimestampWithFormat(
                item.publishedAt,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "MM-dd HH:mm:ss"
            )

            heartImageView.setOnClickListener {
                //TODO 좋아요 삭제
            }
        }
    }
}