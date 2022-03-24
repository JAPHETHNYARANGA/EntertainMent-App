package com.storesoko.entertainmentapp.Movies.network

import com.storesoko.entertainmentapp.Movies.Models.moviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroMovieInterface {
    @GET("movie/550")
    fun getMovieFromAPI(@Query("api_key")apiKey:String):Call<moviesModel>
}