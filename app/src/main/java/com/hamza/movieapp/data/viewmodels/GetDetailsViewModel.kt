package com.hamza.movieapp.data.viewmodels

import androidx.lifecycle.ViewModel
import com.hamza.movieapp.data.local.Dao
import com.hamza.movieapp.data.models.DetailsModel
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.repository.GetDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import javax.inject.Inject

@HiltViewModel
class GetDetailsViewModel @Inject constructor(
    private val repository: GetDetailsRepository,
    private val local: Dao
) :
    ViewModel() {
    var detailsLiveData = repository._detailsLiveData
    val errorLiveData = repository._errorLiveData
    fun getDetails(id: String) {
        return repository.getDetails(id)
    }


}