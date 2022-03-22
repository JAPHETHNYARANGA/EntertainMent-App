package com.storesoko.entertainmentapp.News.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.storesoko.entertainmentapp.News.Models.newsModel
import com.storesoko.entertainmentapp.News.network.RetrofitNewsInstance
import com.storesoko.entertainmentapp.News.network.RetrofitNewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class allNewsViewModel:ViewModel() {
    private var allnewsListData : MutableLiveData<newsModel>


    init {
        allnewsListData = MutableLiveData()
    }

    fun getAllNewsDataObserver():MutableLiveData<newsModel>{
        return allnewsListData
    }

    fun makeAllNewsApiCall(){
        val allNewsRetroInstance = RetrofitNewsInstance.getRetroNewsInstance().create(RetrofitNewsInterface::class.java)
        val allNewsCall = allNewsRetroInstance.getNewsDataFromApi("wsj.com","3d71ad8a2ba04a9a89d36e0eb5910825")

        allNewsCall.enqueue(object : Callback<newsModel>{
            override fun onResponse(call: Call<newsModel>, response: Response<newsModel>) {
                if(response.isSuccessful){
                    allnewsListData.postValue(response.body())
                }else{
                    allnewsListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<newsModel>, t: Throwable) {
                allnewsListData.postValue(null)
            }
        })
    }
}