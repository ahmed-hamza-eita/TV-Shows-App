package com.hamza.movieapp.utils;

import android.view.View
import androidx.fragment.app.Fragment
import com.hamza.movieapp.R
import io.github.muddz.styleabletoast.StyleableToast

fun Fragment.showToast(message: Any?) {
    StyleableToast.makeText(requireContext(), "$message", R.style.toastStyle).show()
}

fun View.visibilityGone() {
    this.visibility = View.GONE
}

fun View.visibilityVisible() {
    this.visibility = View.VISIBLE
}