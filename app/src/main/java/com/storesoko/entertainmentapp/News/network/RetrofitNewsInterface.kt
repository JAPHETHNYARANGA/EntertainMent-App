package com.storesoko.entertainmentapp.News.network

import com.storesoko.entertainmentapp.News.Models.newsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNewsInterface {
    @GET("everything")
    fun getNewsDataFromApi(@Query("domains")domains:String,@Query("apiKey")apiKey:String):Call<newsModel>

    @GET("top-headlines")
    fun getTopHeadlinesDataFromApi(@Query("country")country:String, @Query("apiKey")apiKey:String):Call<newsModel>
}