package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentVideoDetailBinding
import com.example.myapplication.youtubeApi.YoutubeVideoSearchResource
import java.net.URL

class VideoDetailFragment : Fragment() {
    private val TAG = "VideoDetailFragment"
    private val videoData: YoutubeVideo
        get() = requireArguments().getParcelable<YoutubeVideo>(ARG_VIDEO) as YoutubeVideo

    private lateinit var binding: FragmentVideoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)

        initVideo()

        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNavigation(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated")

//        binding?.btnLike?.setOnClickListener {
//            val drawable = {
//                if(isLike) context?.let { ContextCompat.getDrawable(it, R.drawable.ic_drawable_resizefoot) }
//                else context?.let { ContextCompat.getDrawable(it, R.drawable.ic_drawable_resizefoot_blank) }
//            }
//
//            button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
//        }

        binding?.btnShare?.setOnClickListener {
            val url = URL("https://www.youtube.com/watch?v=${YoutubeVideoSearchResource.videoId}")

            val intent = Intent(Intent.ACTION_SEND).apply{
                type = "text/plane"
                putExtra(Intent.EXTRA_TEXT, url)
            }
            startActivity(Intent.createChooser(intent, url))
        }

        initBackButton()

        binding.imgDetailBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNavigation(false)
    }

    // 영상 정보 표시
    private fun initVideo() {
        with(binding) {
            tvDetailTitle.text = videoData.title
            tvDateDetail.text = videoData.publishedAt
            Glide.with(this@VideoDetailFragment)
                .load(videoData.thumbnail)
                .into(imgDetailVideo)
        }
    }

    // 뒤로가기 버튼
    private fun initBackButton() {
        binding.imgDetailBack.setOnClickListener {
            endVideoDetailFragment()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            endVideoDetailFragment()
        }
    }

    private fun endVideoDetailFragment() {
        requireActivity().supportFragmentManager.popBackStack()
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
