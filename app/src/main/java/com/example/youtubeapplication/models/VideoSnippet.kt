package com.example.youtubeapplication.models

import java.io.Serializable

data class VideoSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val liveBroadcastContent: String // This field may vary in your case
):Serializable
