package com.example.youtubeapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.youtubeapplication.adapter.VideoHistoryAdapter
import com.example.youtubeapplication.dao.VideoHistoryDao
import com.example.youtubeapplication.db.AppDatabase
import com.example.youtubeapplication.models.VideoHistory
import com.example.youtubeapplication.repositry.VideoHistoryRepository
import com.example.youtubeapplication.viewmodel.DetailVideoViewModel
import com.example.youtubeapplication.viewmodel.VideoHistoryViewModel
import com.example.youtubeapplication.viewmodel.VideoHistoryViewModelFactory
import com.example.youtubeapplication.viewmodel.VideoViewModelFactory

class VideoHistoryFragment : Fragment() {

    val database =
        Room.databaseBuilder(requireContext(), AppDatabase::class.java, "video_history_db")
            .build()

    private val videoHistoryDao: VideoHistoryDao = database.videoHistoryDao()
    val videoHistoryRepository = VideoHistoryRepository(videoHistoryDao)

    private lateinit var viewModel: VideoHistoryViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoHistoryAdapter: VideoHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video_history, container, false)

        val viewModelFactory = videoHistoryRepository?.let { VideoHistoryViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(this, it)[VideoHistoryViewModel::class.java]
        }!!

        viewModel.recentVideos.observe(viewLifecycleOwner) { videos ->
            // Updating RecyclerView adapter with the list of recent videos
            videoHistoryAdapter = VideoHistoryAdapter(videos)
            videoHistoryAdapter.submitList(videos)
        }

        viewModel.recentVideos

        // Initializing RecyclerView and its adapter
        recyclerView = view.findViewById(R.id.recyclerViewVideoHistory)



        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = videoHistoryAdapter

        return view
    }


}