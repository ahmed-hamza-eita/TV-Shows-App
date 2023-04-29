package com.hamza.movieapp.di

import com.hamza.movieapp.data.network.ApiCalls
import com.hamza.movieapp.utils.Const
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun getApiCalls(): ApiCalls {
      val retrofit=  Retrofit.Builder().baseUrl(Const.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
       return retrofit.create(ApiCalls::class.java)
    }
}