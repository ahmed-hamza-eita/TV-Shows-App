package com.hamza.movieapp.data.network

 import com.hamza.movieapp.data.models.TVShowModel
import retrofit2.Call
 import retrofit2.Response
 import retrofit2.http.GET
 import retrofit2.http.Query
interface ApiCalls {

    @GET("most-popular")
    fun getMostPopularTVShow(@Query("page") page: Int): Response<TVShowModel >


}