package com.hamza.movieapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.databinding.ItemLayoutEposidesBinding

class EpisodesAdapter {

    inner class Holder constructor(private val binding: ItemLayoutEposidesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
    public fun bindEpisode(episode: DetailsModel.TvShow.Episode){
        var title = "S"
        var season = episode.season

    }

}