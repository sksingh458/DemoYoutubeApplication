package com.example.youtubeapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.youtubeapplication.dao.VideoHistoryDao
import com.example.youtubeapplication.models.VideoHistory

@Database(entities = [VideoHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoHistoryDao(): VideoHistoryDao
}