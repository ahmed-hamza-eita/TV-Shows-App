package com.hamza.movieapp.data.network

import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.data.models.TVShowModel
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {


    @GET("most-popular")
    fun getMostPopularTVShow(@Query("page") page: Int): Call<TVShowModel>

    @GET("show-details ")
    fun getDetails(@Query("q") id: String): Call<DetailsModel>

}