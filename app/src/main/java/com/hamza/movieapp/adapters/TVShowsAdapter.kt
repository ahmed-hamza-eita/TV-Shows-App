package com.hamza.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.databinding.ItemContainerTvShowBinding

class TVShowsAdapter : RecyclerView.Adapter<TVShowsAdapter.Holder>() {
 //   lateinit var binding: ItemContainerTvShowBinding
    lateinit var list: ArrayList<TVShowModel.TvShow>
    var onItemClick: OnItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

       val binding = ItemContainerTvShowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)

        //   holder.bindTVShow(data!!)

        holder.binding.apply {
            data?.apply {
                txtName.text = name
                txtStatus.text = status
                txtStarted.text = startDate
                txtNetwork.text = network
                Glide.with(holder.itemView.context).load(imageThumbnailPath).into(imgTvShow);
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder constructor(val binding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.onItemClick(list?.get(layoutPosition)?.id!!)
            }
        }

//        fun bindTVShow(tvShow: TVShowModel.TvShow) {
//            binding.tvShow = tvShow
//            binding.executePendingBindings()
//        }
    }


    interface OnItemClick {
        fun onItemClick(id: Int)
    }
}