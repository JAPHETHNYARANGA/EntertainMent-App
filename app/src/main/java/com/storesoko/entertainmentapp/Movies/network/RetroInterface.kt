package com.storesoko.entertainmentapp.Movies.network

import com.storesoko.entertainmentapp.Movies.Models.characters.characterModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroInterface {
    @GET("characters")
    fun getDataFromInterface():Call<characterModel>
}