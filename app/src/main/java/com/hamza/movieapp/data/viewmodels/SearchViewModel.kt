package com.hamza.movieapp.data.viewmodels

import androidx.lifecycle.ViewModel
import com.hamza.movieapp.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {
    var searchingLiveData = repository._searchLiveData
    val errorLiveData = repository._errorLiveData

    fun searching(tvName: String, page: Int) {

        return repository.searching(tvName, page)


    }
}