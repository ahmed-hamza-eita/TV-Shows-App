package com.hamza.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.data.models.TVShowModel

@Database(entities = [TVShowModel ::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDatabase :RoomDatabase(){
    abstract fun getDao(): Dao
}