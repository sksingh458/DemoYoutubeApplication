package com.example.youtubeapplication.models

data class HomePageResponse(
    val kind: String,
    val etag: String,
    val nextPageToken: String?,
    val regionCode: String,
    val items: List<HomePageItem>
)




