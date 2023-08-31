package com.example.youtubeapplication.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapplication.R
import com.example.youtubeapplication.VideoDetailActivity
import com.example.youtubeapplication.models.VideoDetailItem

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var videoList: List<VideoDetailItem> = ArrayList()
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        Log.d("Video Adapter : OnBindViewHolder", "$position Received $video videos ")
        holder.bind(video)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun submitList(videos: List<VideoDetailItem>) {
        videoList = videos
        notifyDataSetChanged()
        Log.d("VideoAdapter", "Received ${videos?.size ?: 0} videos")
    }

    interface OnItemClickListener {
        fun onItemClick(video: VideoDetailItem)
    }

    // Setter method to set the item click listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoThumbnailImageView: ImageView =
            itemView.findViewById(R.id.videoThumbnailImageView)
        private val videoTitleTextView: TextView = itemView.findViewById(R.id.videoTitleTextView)
        private val videoDescriptionTextView: TextView =
            itemView.findViewById(R.id.videoDescriptionTextView)

        fun bind(video: VideoDetailItem) {
            // Bind data to the views here
            videoTitleTextView.text = video.snippet.title

            videoDescriptionTextView.text = video.snippet.description

            Glide.with(itemView.context)
                .load(video.snippet.thumbnails.high.url)
                .placeholder(R.drawable.placeholder_image)
                .into(videoThumbnailImageView)
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedVideo = videoList[position]

                    // Start the VideoDetailActivity and pass the clicked video data
                    val intent = Intent(itemView.context, VideoDetailActivity::class.java)
                    intent.putExtra("videoData", clickedVideo)
                    itemView.context.startActivity(intent)
                }

            }
        }


    }
}


