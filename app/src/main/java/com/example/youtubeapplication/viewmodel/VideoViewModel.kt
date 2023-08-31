package com.example.youtubeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapplication.models.VideoDetailItem
import com.example.youtubeapplication.repositry.VideoRepository

class VideoViewModel(private val repository: VideoRepository) : ViewModel() {

    val videoList: LiveData<List<VideoDetailItem>> = repository.videoList

    // Call the function to fetch videos from the repository
    fun fetchVideos() {
        repository.fetchVideos()
    }

}
