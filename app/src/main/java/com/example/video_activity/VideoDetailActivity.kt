package com.example.video_activity

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoDetailActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)

        videoView = findViewById(R.id.videoView)

        val videoUrl = intent.getStringExtra("videoUrl")
        playVideo(videoUrl)
    }

    private fun playVideo(videoUrl: String?) {
        videoUrl?.let {
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(Uri.parse(it))
            videoView.requestFocus()
            videoView.start()
        }
    }
}