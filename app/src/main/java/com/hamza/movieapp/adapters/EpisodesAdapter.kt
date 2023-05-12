package com.hamza.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.databinding.ItemContainerTvShowBinding
import com.hamza.movieapp.databinding.ItemLayoutEposidesBinding

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.Holder>() {
    var list: List<DetailsModel.TvShow.Episode>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLayoutEposidesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //  val data = list?.get(position)
        holder.bindEpisode(list?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder constructor(val binding: ItemLayoutEposidesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindEpisode(episode: DetailsModel.TvShow.Episode) {
            var title = "S"
            var season = episode.season
            if (season.length == 1) {
                season = "0".plus(season)
            }
            var eposideNum = episode.episode
            if (eposideNum.length == 1) {
                eposideNum = "0".plus(eposideNum)
            }
            eposideNum = "E".plus(eposideNum)
            title = title.plus(season).plus(eposideNum)
            binding.txtTitle.text = title
            binding.txtName.text = episode.name
            binding.txtAirDate.text = episode.airDate

        }
    }
}