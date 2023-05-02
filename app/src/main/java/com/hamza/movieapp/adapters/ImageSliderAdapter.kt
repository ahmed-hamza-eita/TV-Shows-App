package com.hamza.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.databinding.ItemContainerSliderImageBinding

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.Holder>() {
      var sliderImage: List<String>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemContainerSliderImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //  holder.bindSliderImage(sliderImage?.get(position)!!)
        val data = sliderImage?.get(position)
        Glide.with(holder.itemView.context).load(data).into(holder.binding.imageView);
    }

    override fun getItemCount(): Int {

        return sliderImage?.size ?: 0
    }


    inner class Holder constructor(val binding: ItemContainerSliderImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        fun bindSliderImage(imageUrl: DetailsModel.TvShow) {
//            binding.imageUrl = imageUrl
//        }
    }
}