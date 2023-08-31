package com.example.youtubeapplication.repositry

import androidx.lifecycle.LiveData
import com.example.youtubeapplication.dao.VideoHistoryDao
import com.example.youtubeapplication.models.VideoHistory

class VideoHistoryRepository(private val videoHistoryDao: VideoHistoryDao) {
    fun getRecentVideos(): LiveData<List<VideoHistory>> {
        return videoHistoryDao.getRecentVideos()
    }

    fun insertVideoHistory(videoHistory: VideoHistory) {
        videoHistoryDao.insertVideoHistory(videoHistory)
    }
}
