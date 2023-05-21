package com.hamza.movieapp.data.viewmodels

import android.view.Display.Mode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.repository.MostPopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MostPopularTVShowsViewModel @Inject constructor(private val repository: MostPopularRepository) :
    ViewModel() {

    var mostPopularLiveData = repository._mostPopularLiveData
    val errorLiveData = repository._errorLiveData

    fun getMostPopularTVShows(page: Int) {

        return repository.getMostPopularTVShows(page)


    }


}