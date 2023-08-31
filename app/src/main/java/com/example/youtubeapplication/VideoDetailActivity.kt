package com.example.youtubeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapplication.adapter.VideoAdapter
import com.example.youtubeapplication.adapter.VideoDetailAdapter
import com.example.youtubeapplication.models.VideoDetailItem
import com.example.youtubeapplication.repositry.RetrofitClient
import com.example.youtubeapplication.repositry.VideoRepository
import com.example.youtubeapplication.repositry.YouTubeApiService
import com.example.youtubeapplication.viewmodel.DetailVideoViewModel
import com.example.youtubeapplication.viewmodel.VideoViewModel
import com.example.youtubeapplication.viewmodel.VideoViewModelFactory

class VideoDetailActivity : AppCompatActivity() {

    private val apiService = RetrofitClient.instance?.create(YouTubeApiService::class.java)

    private lateinit var viewModel: DetailVideoViewModel
    private lateinit var videoDetailAdapter: VideoDetailAdapter

    private val repository = apiService?.let{ VideoRepository(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val videoDetail: VideoDetailItem? =
            intent.getSerializableExtra("videoData") as? VideoDetailItem

        // Bind data to the views
        val videoTitleTextView: TextView = findViewById(R.id.textViewVideoTitle)
        val videoDescriptionTextView: TextView = findViewById(R.id.textViewVideoDescription)
        val imageView: ImageView = findViewById(R.id.imageViewVideo)

        videoDetailAdapter = VideoDetailAdapter()

        if (videoDetail != null) {
            // Now you can access properties of videoDetail like title and description
            Log.d("VideoDetailsActivity Title passed:", videoDetail.snippet.title)
            Log.d("VideoDetailsActivity Description passed:", videoDetail.snippet.description)
            videoTitleTextView.text = videoDetail.snippet.title
            videoDescriptionTextView.text = videoDetail.snippet.description

            Glide.with(this)
                .load(videoDetail.snippet.thumbnails.high.url)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)


        }else{
            Log.d("VideoDetailsActivity Title passed:", " is null")
            Log.d("VideoDetailsActivity Description passed:", " is null")
        }


        val viewModelFactory = repository?.let { VideoViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(this, it)[DetailVideoViewModel::class.java]
        }!!

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewDetailsVideos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = videoDetailAdapter

        viewModel.videoList.observe(this) { videos ->
            // Updating the RecyclerView adapter with the new list of videos
            val sortedVideos = videos.sortedByDescending { it.snippet.publishedAt}
            videoDetailAdapter.submitList(sortedVideos)
            Log.d("Detail Activity" , "" + videos[1])
            Log.d("Detail Activity", "Received ${videos?.size ?: 0} videos")
        }

        // Fetch videos when the activity is created or when needed
        viewModel.fetchDetailVideos()
    }
}
