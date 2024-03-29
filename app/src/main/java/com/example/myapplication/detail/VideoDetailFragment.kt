package com.example.myapplication.detail

import android.content.Intent
import android.os.Build
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.FragmentVideoDetailBinding
import com.example.myapplication.like.OnHeartClickedListener
import com.example.myapplication.watchlist.OnWatchListener
import java.net.URL
import java.text.DecimalFormat

class VideoDetailFragment : Fragment() {
    private val TAG = "VideoDetailFragment"
    private val videoData: YoutubeVideo
        get() = requireArguments().getParcelable<YoutubeVideo>(ARG_VIDEO) as YoutubeVideo

    private var _binding: FragmentVideoDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var commentAdapter:CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OnWatchListener.onWatch(videoData)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoDetailBinding.inflate(inflater, container, false)

        initVideo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideBottomNavigation(true)

        initHeartButton()
        initShareButton()

        initBackButton()

        initCommentRecyclerView()
        initReplyButton()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initVideo() {
        with(binding) {
            Glide.with(this@VideoDetailFragment)
                .load(videoData.thumbnail)
                .into(ivVideo)

            tvTitle.text = videoData.title
            tvAuthor.text = videoData.author
            tvDate.text = videoData.publishedAt

            val dec = DecimalFormat("#,###")
            tvViewcount.text = "${dec.format(videoData.viewCount)}회"
        }
    }

    private fun initHeartButton(){
        val fullHeart = resources.getDrawable(R.drawable.ic_drawable_resizefoot)
        val emptyHeart = resources.getDrawable(R.drawable.ic_drawable_resizefoot_line)

        if(videoData.isLiked) binding.btnLike.setLeftDrawable(fullHeart)

        binding.btnLike.setOnClickListener {
            Log.d(TAG, "like button clicked")

            videoData.isLiked = !videoData.isLiked
            binding.btnLike.setLeftDrawable(
                if(videoData.isLiked) fullHeart else emptyHeart
            )
            OnHeartClickedListener.onHeartClicked(videoData)
        }
    }
    private fun Button.setLeftDrawable(drawable: Drawable){
        //Log.d(TAG, "change left drawable")
        //drawable.setBounds( 0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight);
        this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }


    private fun initShareButton(){
        binding.btnShare.setOnClickListener {
            val url = URL("https://www.youtube.com/watch?v=${videoData.id}").toString()

            val intent = Intent(Intent.ACTION_SEND).apply{
                type = "text/plane"
                putExtra(Intent.EXTRA_TEXT, url)
            }
            startActivity(Intent.createChooser(intent, url))
        }
    }

    private fun initBackButton() {
        binding.ivBack.setOnClickListener {
            endVideoDetailFragment()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            endVideoDetailFragment()
        }
    }

    private fun endVideoDetailFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        (activity as MainActivity).hideBottomNavigation(false)
    }

    private fun initReplyButton(){
        binding.btnReply.setOnClickListener {
            if(binding.etComment.text.isNullOrBlank())
                return@setOnClickListener

            val newComment = Comment(videoData.id, binding.etComment.text.toString())
            CommentUtils.saveComment(newComment)
            commentAdapter.updateItems(CommentUtils.getVideoComments(videoData.id))
        }
    }

    private fun initCommentRecyclerView(){
        commentAdapter = CommentAdapter()
        commentAdapter.updateItems(CommentUtils.getVideoComments(videoData.id))
        binding.recyclerviewReply.adapter = commentAdapter
    }

    //Parcelize
    companion object {
        private const val ARG_VIDEO = "argVideo"

        fun newInstance(youtubeVideo: YoutubeVideo) =
            VideoDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_VIDEO, youtubeVideo)
                }
            }

    }
}
