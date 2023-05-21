package com.hamza.movieapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.hamza.movieapp.data.local.Dao
import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.data.network.ApiCalls
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetDetailsRepository @Inject constructor(private val api: ApiCalls, private val local: Dao) {

    val _detailsLiveData = MutableLiveData<DetailsModel?>()
    val _errorLiveData = MutableLiveData<String>()

    fun getDetails(id: String) {
        api.getDetails(id).enqueue(object : Callback<DetailsModel> {
            override fun onResponse(call: Call<DetailsModel>, response: Response<DetailsModel>) {
                _detailsLiveData.value = response.body()
                _detailsLiveData.postValue(response.body())

            }

            override fun onFailure(call: Call<DetailsModel>, t: Throwable) {
                _errorLiveData.value = t.message
                _errorLiveData.postValue(t.message)

            }
        })
    }
}