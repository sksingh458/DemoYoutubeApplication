package com.example.youtubeapplication.models

data class VideoDetailResponse(
    val kind: String,
    val etag: String,
    val items: List<VideoDetailItem>
)

