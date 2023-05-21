package com.hamza.movieapp.data.local;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hamza.movieapp.data.models.TVShowModel
import java.lang.reflect.Type



class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromJson(json: String): List<TVShowModel.TvShow>? {
        val listType = object : TypeToken<List<TVShowModel.TvShow>>() {}.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun toJson(tvShows: List<TVShowModel.TvShow>?): String {
        return gson.toJson(tvShows)
    }

}