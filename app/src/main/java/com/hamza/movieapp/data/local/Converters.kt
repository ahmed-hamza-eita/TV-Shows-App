package com.hamza.movieapp.data.local;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromString(value: String): List<Int> {
        val listType: Type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    fun fromArrayToJson(list: List<Int>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    private val gson = Gson()

    @TypeConverter
    fun fromJson(json: String?): Any? {
        return gson.fromJson(json, Any::class.java)
    }

    @TypeConverter
    fun toJson(data: Any?): String? {
        return gson.toJson(data)
    }
}