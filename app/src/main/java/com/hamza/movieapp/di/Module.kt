package com.hamza.movieapp.di

import android.content.Context
import androidx.room.Room
import com.hamza.movieapp.data.local.Dao
import com.hamza.movieapp.data.local.MyDatabase
import com.hamza.movieapp.data.network.ApiCalls
import com.hamza.movieapp.utils.Const
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun getApiCalls(): ApiCalls {
        val retrofit = Retrofit.Builder().baseUrl(Const.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getRoomDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, Const.DB_NAME)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun getDao(myDB: MyDatabase): Dao = myDB.getDao()
}