package com.example.video_activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter
    private var videoList = mutableListOf<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        videoAdapter = VideoAdapter(videoList, this) { selectedVideo ->
            // Abrir una nueva Actividad y pasar la información del video
            val intent = Intent(this, VideoDetailActivity::class.java)
            intent.putExtra("videoUrl", selectedVideo.sources.first())
            startActivity(intent)
        }

        recyclerView.adapter = videoAdapter

        // Llamar a la API y cargar los datos en el RecyclerView
        loadVideos()
    }

    private fun loadVideos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://51822719-2fc7-4d59-ac52-3297a79dd740.mock.pstmn.io//")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(ApiService::class.java)
        val call = movieApi.getMovies()

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val allVideos = mutableListOf<Video>()
                    response.body()?.categories?.forEach { category ->
                        allVideos.addAll(category.videos)
                    }
                    videoList.addAll(allVideos)
                    videoAdapter.notifyDataSetChanged()

                    allVideos.forEach { video ->
                        println("Title: ${video.title}, Subtitle: ${video.subtitle}")
                    }
                } else {
                    // Manejar el código de respuesta no exitoso
                    Toast.makeText(this@MainActivity, "Error al cargar los videos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Manejar el error de la llamada
                Toast.makeText(this@MainActivity, "Error de red", Toast.LENGTH_SHORT).show()
            }
        })
    }
}