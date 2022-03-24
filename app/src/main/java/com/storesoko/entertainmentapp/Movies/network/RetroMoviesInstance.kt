package com.storesoko.entertainmentapp.Movies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroMoviesInstance {
    companion object{
        val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"

        fun getMoviesRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}