package com.storesoko.entertainmentapp.Movies.network


import com.storesoko.entertainmentapp.Movies.Models.fishes.mainModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroInterface {
    @GET("characters")
    fun getDataFromInterface(): Call<mainModel>
}