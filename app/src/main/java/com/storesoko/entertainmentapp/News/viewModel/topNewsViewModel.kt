package com.storesoko.entertainmentapp.News.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.storesoko.entertainmentapp.News.Models.newsModel
import com.storesoko.entertainmentapp.News.network.RetrofitNewsInstance
import com.storesoko.entertainmentapp.News.network.RetrofitNewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class topNewsViewModel: ViewModel() {

    lateinit var topNewsListData : MutableLiveData<newsModel>

    init {
        topNewsListData = MutableLiveData()
    }

    fun getTopNewsDataObserver(): MutableLiveData<newsModel>{
        return topNewsListData
    }

    fun maketopNewsApiCall(){
        val topNewsRetroInstance = RetrofitNewsInstance.getRetroNewsInstance().create(RetrofitNewsInterface::class.java)
        val topNewsCall = topNewsRetroInstance.getTopHeadlinesDataFromApi("us","3d71ad8a2ba04a9a89d36e0eb5910825")


        topNewsCall.enqueue(object: Callback<newsModel>{
            override fun onResponse(call: Call<newsModel>, response: Response<newsModel>) {
                if(response.isSuccessful){
                    topNewsListData.postValue(response.body())
                }else{
                    topNewsListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<newsModel>, t: Throwable) {
                topNewsListData.postValue(null)
            }
        })
    }
}