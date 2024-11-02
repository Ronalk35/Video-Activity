package com.example.video_activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoAdapter(
    private val videoList: List<Video>,
    private val context: Context,
    private val onVideoClickListener: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]

        println("Binding data for position $position: Title=${video.title}, Subtitle=${video.subtitle}")

        holder.titleTextView.text = video.title
        holder.subtitleTextView.text = video.subtitle

        holder.titleTextView.text = video.title
        holder.subtitleTextView.text = video.subtitle

        // Agregar el OnClickListener al elemento de la lista
        holder.itemView.setOnClickListener {
            onVideoClickListener.invoke(video)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
    }
}