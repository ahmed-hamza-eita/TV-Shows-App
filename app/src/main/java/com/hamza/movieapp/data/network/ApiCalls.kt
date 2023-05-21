package com.hamza.movieapp.data.network

import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.data.models.TVShowModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {


    @GET("most-popular")
    fun getMostPopularTVShow(@Query("page") page: Int): Call<TVShowModel>

    @GET("show-details ")
    fun getDetails(@Query("q") id: String): Call<DetailsModel>

    @GET("search")
    fun searching(@Query("q") tvName: String, @Query("page") page: Int): Call<TVShowModel>

}