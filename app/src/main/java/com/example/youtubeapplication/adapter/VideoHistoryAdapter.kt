package com.example.youtubeapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapplication.R
import com.example.youtubeapplication.models.VideoHistory

class VideoHistoryAdapter(private val videoHistoryList: List<VideoHistory>) :
    RecyclerView.Adapter<VideoHistoryAdapter.VideoHistoryViewHolder>() {

    private var videoList: List<VideoHistory> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video_history, parent, false)
        return VideoHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHistoryViewHolder, position: Int) {
        val videoHistoryItem = videoHistoryList[position]
        holder.bind(videoHistoryItem)
    }

    override fun getItemCount(): Int {
        return videoHistoryList.size
    }

    fun submitList(newList: List<VideoHistory>) {
        videoList = newList
        notifyDataSetChanged()
    }

    inner class VideoHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoTitleTextView: TextView = itemView.findViewById(R.id.videoHistoryTitle)
        private val videoDescriptionTextView: TextView =
            itemView.findViewById(R.id.videoHistoryDescription)
        private val watchDateTextView: TextView = itemView.findViewById(R.id.watchDateTextView)

        fun bind(videoHistory: VideoHistory) {
            videoTitleTextView.text = videoHistory.title
            videoDescriptionTextView.text = videoHistory.description
            watchDateTextView.text = videoHistory.timestamp.toString()
        }
    }
}
