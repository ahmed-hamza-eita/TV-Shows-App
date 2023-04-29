package com.hamza.movieapp.adapters

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BindingAdapter {

    @androidx.databinding.BindingAdapter("android:imageURL")
    fun setImageUrl(imageView: ImageView, url: String) {
        try {
            imageView.alpha = 0f
            Picasso.get().load(url).into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start()
                }

                override fun onError(e: java.lang.Exception?) {
                    TODO("Not yet implemented")
                }
            })
        } catch (ignored: Exception) {

        }
    }
}