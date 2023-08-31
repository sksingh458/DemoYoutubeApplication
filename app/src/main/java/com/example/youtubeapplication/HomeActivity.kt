package com.example.youtubeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapplication.adapter.VideoAdapter
import com.example.youtubeapplication.models.VideoDetailItem
import com.example.youtubeapplication.repositry.RetrofitClient
import com.example.youtubeapplication.repositry.VideoRepository
import com.example.youtubeapplication.repositry.YouTubeApiService
import com.example.youtubeapplication.viewmodel.VideoViewModel
import com.example.youtubeapplication.viewmodel.VideoViewModelFactory


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: VideoViewModel
    private lateinit var videoAdapter: VideoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val apiService = RetrofitClient.instance?.create(YouTubeApiService::class.java)

        val repository = apiService?.let{ VideoRepository(it) }

        val viewModelFactory = repository?.let { VideoViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(this, it)[VideoViewModel::class.java]
        }!!

        videoAdapter = VideoAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewVideos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = videoAdapter

        viewModel.videoList.observe(this) { videos ->
            // Updating the RecyclerView adapter with the new list of videos
            val sortedVideos = videos.sortedByDescending { it.snippet.publishedAt}
            videoAdapter.submitList(sortedVideos)
            Log.d("Home Activity" , "" + videos[1])
            Log.d("Home Activity", "Received ${videos?.size ?: 0} videos")
        }

        // Fetch videos when the activity is created or when needed
        viewModel.fetchVideos()
    }

}
