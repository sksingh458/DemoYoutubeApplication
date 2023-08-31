package com.example.youtubeapplication.models

import java.io.Serializable

data class VideoId(
    val kind: String,
    val videoId: String
):Serializable