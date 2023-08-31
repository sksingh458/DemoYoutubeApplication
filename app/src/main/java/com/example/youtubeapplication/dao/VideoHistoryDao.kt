package com.example.youtubeapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youtubeapplication.models.VideoHistory

@Dao
interface VideoHistoryDao {
    @Query("SELECT * FROM video_history ORDER BY timestamp DESC LIMIT 10")
    fun getRecentVideos(): LiveData<List<VideoHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideoHistory(videoHistory: VideoHistory)

}