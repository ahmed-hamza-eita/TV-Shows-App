package com.hamza.movieapp.data.models


import com.google.gson.annotations.SerializedName

data class TVShowModel(
    @SerializedName("page")
    val page: Int ?=null,
    @SerializedName("pages")
    val pages: Int ?=null,
    @SerializedName("total")
    val total: String ?=null,
    @SerializedName("tv_shows")
    val tvShows: List<TvShow> ?=null
) {
    data class TvShow(
        @SerializedName("country")
        val country: String,
        @SerializedName("end_date")
        val endDate: Any,
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