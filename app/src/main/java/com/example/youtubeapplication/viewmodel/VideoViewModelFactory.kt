package com.example.youtubeapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapplication.repositry.VideoRepository


class VideoViewModelFactory(private val repository: VideoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VideoViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailVideoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailVideoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}
