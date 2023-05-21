package com.hamza.movieapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.network.ApiCalls
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class SearchRepository  @Inject constructor(private val api: ApiCalls) {
    val _searchLiveData = MutableLiveData<TVShowModel?>()
    val _errorLiveData = MutableLiveData<String>()

    fun searching(tvName: String, page: Int) {
        api.searching(tvName, page).enqueue(object : Callback, retrofit2.Callback<TVShowModel> {
            override fun onResponse(call: Call<TVShowModel>, response: Response<TVShowModel>) {
                _searchLiveData.value = response.body()
                _searchLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<TVShowModel>, t: Throwable) {
                val _errorLiveData = MutableLiveData<String>()

            }
        })
    }
}