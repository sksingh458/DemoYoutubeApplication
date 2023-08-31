package com.example.youtubeapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_history")
data class VideoHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val videoId: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val timestamp: Long
)