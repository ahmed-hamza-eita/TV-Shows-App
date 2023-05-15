package com.hamza.movieapp.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamza.movieapp.data.models.TVShowModel
import io.reactivex.Completable
import io.reactivex.Flowable

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM tvShows")
    fun getWatchList(): Flowable<List<TVShowModel.TvShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToWatchlist(tvShow: TVShowModel.TvShow): Completable

    @Delete
    fun removeFromWishList(tvShow: TVShowModel.TvShow)


}

