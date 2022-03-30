package com.storesoko.entertainmentapp.Age.network

import com.storesoko.entertainmentapp.Age.models.dataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroInterface {
    @GET("data")
    fun getDataFromApi(@Query("drilldowns")Nation:String, @Query("measures")Population:String): Call<dataModel>
}