package com.example.youtubeapplication.repositry

import com.example.youtubeapplication.models.VideoDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("search")
    fun getVideos(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String
    ): Call<VideoDetailResponse>

    @GET("search")
    fun getDetailVideos(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String
    ): Call<VideoDetailResponse>

}


