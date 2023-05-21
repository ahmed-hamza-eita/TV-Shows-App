package com.hamza.movieapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.hamza.movieapp.data.local.Dao
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.network.ApiCalls

import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MostPopularRepository @Inject constructor(private val api: ApiCalls, private val local: Dao) {


    val _mostPopularLiveData = MutableLiveData<TVShowModel?>()
    val _errorLiveData = MutableLiveData<String>()

    fun getMostPopularTVShows(page: Int) {
        api.getMostPopularTVShow(page)
            .enqueue(object : Callback, retrofit2.Callback<TVShowModel> {
                override fun onResponse(
                    call: Call<TVShowModel>,
                    response: Response<TVShowModel>
                ) {
                    _mostPopularLiveData.value = response.body()
                    _mostPopularLiveData.postValue(response.body())
                    local.setDataLocal(response.body()!!)
                }

                override fun onFailure(call: Call<TVShowModel>, t: Throwable) {
                    _errorLiveData.value = t.message
                    _errorLiveData.postValue(t.message)
                    local.getDataLocal()
                }
            })
    }



}