package com.example.myapplication.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.ShortsItemBinding
import com.example.myapplication.databinding.VideoitemBinding

class HomeAdapter(val mContext: Context) : RecyclerView.Adapter<ViewHolder>() {

    var items = ArrayList<YoutubeVideo>()

    companion object{
        private const val VIEW_TYPE_VIDEO = 1
        private const val VIEW_TYPE_SHORTS = 2
    }
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType){
            VIEW_TYPE_VIDEO ->{
                val binding = VideoitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VideoViewHolder(binding)
            } else ->{
                val binding = ShortsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ShortsViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val item = items[position]){

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class VideoViewHolder(binding: VideoitemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var iv_thum_video: ImageView = binding.ivVideoimage
        var iv_like: ImageView = binding.ivVideoLike
        var iv_video_name: TextView = binding.tvVideoName
        var iv_video_time: TextView = binding.tvVideoTime

        init {
            iv_thum_video.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]
        }
    }

    inner class ShortsViewHolder(binding: ShortsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        var iv_thum_shorts: ImageView = binding.ivShorstimage
        var iv_shorts_like: ImageView = binding.ivShorstLike
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

}