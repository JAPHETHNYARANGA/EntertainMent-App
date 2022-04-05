package com.storesoko.entertainmentapp.Movies.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.storesoko.entertainmentapp.Movies.Models.characters.characterModel
import com.storesoko.entertainmentapp.Movies.network.RetroInstance
import com.storesoko.entertainmentapp.Movies.network.RetroInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class characterViewModel :ViewModel() {
    lateinit var characterListData : MutableLiveData<characterModel>

    init {
        characterListData = MutableLiveData()
    }

    fun getCharacterDataObserver():MutableLiveData<characterModel>{
        return characterListData
    }

    fun makeCharacterApiCall(){
        val characterRetroInstance = RetroInstance.getCharacterRetroInstance().create(RetroInterface::class.java)
        val characterCall = characterRetroInstance.getDataFromInterface()


        characterCall.enqueue(object : Callback<characterModel>{
            override fun onResponse(
                call: Call<characterModel>,
                response: Response<characterModel>
            ) {
                if(response.isSuccessful){
                    characterListData.postValue(response.body())
                }else{
                    characterListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<characterModel>, t: Throwable) {
                characterListData.postValue(null)
            }
        })
    }
}