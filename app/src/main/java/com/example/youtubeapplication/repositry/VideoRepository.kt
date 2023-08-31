package com.example.youtubeapplication.repositry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapplication.models.VideoDetailItem
import com.example.youtubeapplication.models.VideoDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoRepository(private val apiService: YouTubeApiService) {

    //home activity list
    private val _videoListLiveData: MutableLiveData<List<VideoDetailItem>> = MutableLiveData()
    val videoList: LiveData<List<VideoDetailItem>> = _videoListLiveData

    //detail activity list
    private val _videoDetailListLiveData: MutableLiveData<List<VideoDetailItem>> = MutableLiveData()
    val videoDetailList: LiveData<List<VideoDetailItem>> = _videoDetailListLiveData

    private val part = "id,snippet"
    private val maxResults = 50
    private val channelId = "UCVMWWQ985A_-SESZUy_SsVQ"
    private val apiKey = "AIzaSyAityvKSb8_MZFnq9cuKOniroFkkA-L_SE"

    //fetching list for home activity
    fun fetchVideos() {

        apiService.getVideos(part, maxResults, channelId, apiKey).enqueue(object :
            Callback<VideoDetailResponse> {
            override fun onResponse(call: Call<VideoDetailResponse>, response: Response<VideoDetailResponse>) {
                if (response.isSuccessful) {
                    // Now you can access videoDetailResponse.videos, which is a List<VideoDetailItem>
                    // Handle the data as needed
                    val videoDetailResponse = response.body()
                    Log.d("Video Repository" , "" + (videoDetailResponse?.items?.size ?:0))
                    if (videoDetailResponse != null) {
                        Log.d("Video Repository" , "" + videoDetailResponse.items[0])
                    }
                    _videoListLiveData.postValue(videoDetailResponse?.items)
                } else {
                    // Handling the error here
                }
            }

            override fun onFailure(call: Call<VideoDetailResponse>, t: Throwable) {
                // Handling network error
            }
        })
    }

    //fetching list for detail activity
    fun fetchDetailVideos() {

        val channelId = "UCk8LWzqGcHz21FWysiXuCHw"

        apiService.getDetailVideos(part, maxResults, channelId, apiKey).enqueue(object :
            Callback<VideoDetailResponse> {
            override fun onResponse(call: Call<VideoDetailResponse>, response: Response<VideoDetailResponse>) {
                if (response.isSuccessful) {

                    val videoDetailResponse = response.body()
                    Log.d("Video Repository" , "" + (videoDetailResponse?.items?.size ?:0))
                    if (videoDetailResponse != null) {
                        Log.d("Video Repository" , "" + videoDetailResponse.items[0])
                    }
                    _videoDetailListLiveData.postValue(videoDetailResponse?.items)
                } else {
                    // Handling the error here
                }
            }

            override fun onFailure(call: Call<VideoDetailResponse>, t: Throwable) {
                // Handling network error
            }
        })
    }

}
