package com.storesoko.entertainmentapp.News.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.storesoko.entertainmentapp.News.Models.newsModel
import com.storesoko.entertainmentapp.News.network.RetrofitNewsInstance
import com.storesoko.entertainmentapp.News.network.RetrofitNewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class searchNewsViewModel : ViewModel() {
    lateinit var searchRecyclerListData : MutableLiveData<newsModel>

    init {
        searchRecyclerListData = MutableLiveData()
    }

    fun getSearchRecyclerListDataObserver(): MutableLiveData<newsModel>{
        return searchRecyclerListData
    }
    fun makeSearchApiCall(input:String){
        val searchRetroInstance = RetrofitNewsInstance.getRetroNewsInstance().create(RetrofitNewsInterface::class.java)
        val searchCall = searchRetroInstance.getSearchDataFromApi(input, "3d71ad8a2ba04a9a89d36e0eb5910825")


        searchCall.enqueue(object : Callback<newsModel>{
            override fun onResponse(call: Call<newsModel>, response: Response<newsModel>) {
                if (response.isSuccessful){
                    searchRecyclerListData.postValue(response.body())
                }else{
                    searchRecyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<newsModel>, t: Throwable) {
                searchRecyclerListData.postValue(null)
            }
        })
    }
}