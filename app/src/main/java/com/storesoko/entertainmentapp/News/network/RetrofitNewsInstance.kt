package com.storesoko.entertainmentapp.News.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNewsInstance {
    companion object{
        val BASE_URL = "https://newsapi.org/v2/"

        fun getRetroNewsInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}