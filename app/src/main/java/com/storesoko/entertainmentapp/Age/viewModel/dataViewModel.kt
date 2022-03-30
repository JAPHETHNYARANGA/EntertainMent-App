package com.storesoko.entertainmentapp.Age.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.storesoko.entertainmentapp.Age.models.dataModel
import com.storesoko.entertainmentapp.Age.network.RetroInstance
import com.storesoko.entertainmentapp.Age.network.RetroInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class dataViewModel :ViewModel(){
    lateinit var recyclerListData : MutableLiveData<dataModel>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver() : MutableLiveData<dataModel>{
        return recyclerListData
    }

    fun makeApiCall(){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroInterface::class.java)
        val call = retroInstance.getDataFromApi("Nation","Population")


        call.enqueue(object: Callback<dataModel>{
            override fun onResponse(call: Call<dataModel>, response: Response<dataModel>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<dataModel>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }



}