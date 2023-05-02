package com.hamza.movieapp.data.viewmodels

import androidx.lifecycle.ViewModel
import com.hamza.movieapp.data.repository.GetDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetDetailsViewModel @Inject constructor(private val repository: GetDetailsRepository) :
    ViewModel() {
    var detailsLiveData = repository._detailsLiveData
    val errorLiveData = repository._errorLiveData
    fun getDetails(id: String) {
        return repository.getDetails(id)
    }
}