package com.example.youtubeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapplication.models.VideoDetailItem
import com.example.youtubeapplication.repositry.VideoRepository

class DetailVideoViewModel(private val repository: VideoRepository) : ViewModel() {

    val videoList: LiveData<List<VideoDetailItem>> = repository.videoDetailList

    // Call the function to fetch videos from the repository
    fun fetchDetailVideos() {
        repository.fetchDetailVideos()
    }

}