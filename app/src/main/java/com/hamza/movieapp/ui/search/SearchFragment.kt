package com.hamza.movieapp.ui.search;

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.hamza.movieapp.R
import com.hamza.movieapp.adapters.TVShowsAdapter
import com.hamza.movieapp.data.models.TVShowModel
import com.hamza.movieapp.data.viewmodels.SearchViewModel
import com.hamza.movieapp.databinding.SearchFragmentBinding
import com.hamza.movieapp.utils.BaseFragment
import com.hamza.movieapp.utils.ProgressLoading
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val adapter = TVShowsAdapter()
    private val timer = Timer()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.search_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SearchFragmentBinding.bind(view)


        actions()
        observer()

    }

    private fun observer() {

        viewModel.searchingLiveData.observe(viewLifecycleOwner) {
            binding.rvTvShow.setHasFixedSize(true)
            adapter.list = it?.tvShows as ArrayList<TVShowModel.TvShow>
            binding.rvTvShow.adapter = adapter

            ProgressLoading.dismiss()
        }
    }

    private fun actions() {
        binding.imgBack.setOnClickListener {
            navigate(SearchFragmentDirections.actionSearchFragmentToMostPopularTVShowsFragment())
        }
//        binding.imgSearch.setOnClickListener {
//            ProgressLoading.show(requireActivity())
//            viewModel.searching(binding.txtInputSearch.text.toString(), 1)
//        }

        binding.txtInputSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (timer != null) {
                    timer.cancel()
                }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun afterTextChanged(s: Editable?) {
                ProgressLoading.show(requireActivity())

                if (s.toString().trim().isNotEmpty()) {
                    val timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            Handler(Looper.getMainLooper()).post {
                                ProgressLoading.dismiss()

                                viewModel.searching(s.toString(), 1)
                            }
                        }
                    }, 800)
                } else {
                    adapter.list.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        })
        adapter.onItemClick = object : TVShowsAdapter.OnItemClick {
            override fun onItemClick(id: Int) {
                navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id.toString()))
            }
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}