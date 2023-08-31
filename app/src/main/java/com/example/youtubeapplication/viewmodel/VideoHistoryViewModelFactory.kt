package com.example.youtubeapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapplication.repositry.VideoHistoryRepository
import com.example.youtubeapplication.repositry.VideoRepository

class VideoHistoryViewModelFactory(private val repository: VideoHistoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoHistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VideoHistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}