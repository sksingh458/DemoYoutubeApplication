package com.example.youtubeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapplication.models.VideoHistory
import com.example.youtubeapplication.repositry.VideoHistoryRepository

class VideoHistoryViewModel(private val videoHistoryRepository: VideoHistoryRepository) : ViewModel() {
    val recentVideos: LiveData<List<VideoHistory>> = videoHistoryRepository.getRecentVideos()

    fun addVideoToHistory(videoHistory: VideoHistory) {
            videoHistoryRepository.insertVideoHistory(videoHistory)
    }
}
