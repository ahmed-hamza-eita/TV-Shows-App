package com.hamza.movieapp.ui.home;

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.hamza.movieapp.R
import com.hamza.movieapp.adapters.TVShowsAdapter
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.viewmodels.MostPopularTVShowsViewModel
import com.hamza.movieapp.databinding.MostPopularShowFragmentBinding
import com.hamza.movieapp.utils.BaseFragment
import com.hamza.movieapp.utils.ProgressLoading
import com.hamza.movieapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularTVShowsFragment : BaseFragment() {

    private var _binding: MostPopularShowFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MostPopularTVShowsViewModel by viewModels()
    private val adapter = TVShowsAdapter()
 //   private val list = ArrayList<TVShowModel.TvShow>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.most_popular_show_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MostPopularShowFragmentBinding.bind(view)
        viewModel.getMostPopularTVShows(0)
        observer()

    }



    private fun observer() {
        ProgressLoading.show(myActivity!!)
        viewModel.mostPopularLiveData.observe(viewLifecycleOwner) {
            ProgressLoading.dismiss()
//            if (it != null) {
//                if (it.tvShows != null) {
//                    adapter.list = it .tvShows as ArrayList<TVShowModel.TvShow>?
//                    binding.rvTvShow.adapter = adapter
//
//                    adapter.notifyDataSetChanged()
//                }
//            }

            binding.rvTvShow.setHasFixedSize(true)

            adapter.list =it?.tvShows as ArrayList<TVShowModel.TvShow>
            binding.rvTvShow.adapter = adapter
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            ProgressLoading.dismiss()
            showToast(it)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}