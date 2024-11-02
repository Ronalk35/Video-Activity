package com.example.video_activity

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("videos")
    fun getMovies(): Call<MovieResponse>
}