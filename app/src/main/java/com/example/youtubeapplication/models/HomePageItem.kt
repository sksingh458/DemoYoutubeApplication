package com.example.youtubeapplication.models

data class HomePageItem(
    val videoId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val publishedAt: String
)

