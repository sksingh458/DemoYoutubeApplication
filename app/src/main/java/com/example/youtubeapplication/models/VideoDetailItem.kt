package com.example.youtubeapplication.models

import java.io.Serializable

data class VideoDetailItem(

    val kind: String,
    val etag: String,
    val id: VideoId,
    val snippet: VideoSnippet
) : Serializable
