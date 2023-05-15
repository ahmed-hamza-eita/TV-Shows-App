package com.hamza.movieapp.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.hamza.movieapp.data.local.Converters

data class TVShowModel(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("total")
    val total: String? = null,
    @SerializedName("tv_shows")
    @TypeConverters(Converters::class)
    val tvShows: List<TvShow>? = null
) {
    @Entity(tableName = "tvShows")
    data class TvShow(
        @SerializedName("country")
        val country: String,
        @TypeConverters(Converters::class)
        @SerializedName("end_date")
        val endDate: Any,
        @PrimaryKey
        @SerializedName("id")
        val id: Int,
        @SerializedName("image_thumbnail_path")
        val imageThumbnailPath: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("network")
        val network: String,
        @SerializedName("permalink")
        val permalink: String,
        @SerializedName("start_date")
        val startDate: String,
        @SerializedName("status")
        val status: String
    )
}