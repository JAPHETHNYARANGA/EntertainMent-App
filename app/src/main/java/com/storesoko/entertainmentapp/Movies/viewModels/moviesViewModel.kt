package com.storesoko.entertainmentapp.Movies.viewModels

import androidx.lifecycle.MutableLiveData
import com.storesoko.entertainmentapp.Movies.Models.moviesModel
import com.storesoko.entertainmentapp.Movies.network.RetroMovieInterface
import com.storesoko.entertainmentapp.Movies.network.RetroMoviesInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class moviesViewModel {
    lateinit var allMoviesListData : MutableLiveData<moviesModel>

    init {
        allMoviesListData = MutableLiveData()
    }

    fun getAllMoviesDataObserver():MutableLiveData<moviesModel>{
        return allMoviesListData
    }

    fun makeAllMoviesApiCall(){
        val allMoviesRetroInstance = RetroMoviesInstance.getMoviesRetroInstance().create(RetroMovieInterface::class.java)

        val allMoviesCall = allMoviesRetroInstance.getMovieFromAPI("236c0612a9da20170b62603163bb93f9")


        allMoviesCall.enqueue(object : Callback<moviesModel>{
            override fun onResponse(call: Call<moviesModel>, response: Response<moviesModel>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<moviesModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}