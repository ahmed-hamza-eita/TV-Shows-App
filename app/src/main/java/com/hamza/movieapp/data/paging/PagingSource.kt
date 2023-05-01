package com.hamza.movieapp.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingState
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.network.ApiCalls
import com.hamza.movieapp.data.repository.MostPopularRepository

class PagingSource(private val repository: ApiCalls) :
    androidx.paging.PagingSource<Int, TVShowModel.TvShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowModel.TvShow> {
        val page = params.key ?: 1
        return try {
            val data = repository.getMostPopularTVShow(page)
            LoadResult.Page(
                data = data.body()?.tvShows!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.tvShows?.isEmpty()!!) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVShowModel.TvShow>): Int? {
        return state.anchorPosition?.let {
            val anchorPosition = state.closestPageToPosition(it)
            anchorPosition?.prevKey?.plus(1) ?: anchorPosition?.nextKey?.minus(1)
        }
    }

}