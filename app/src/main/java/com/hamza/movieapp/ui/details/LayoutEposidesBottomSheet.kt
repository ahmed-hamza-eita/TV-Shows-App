package layout;

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hamza.movieapp.R
import com.hamza.movieapp.adapters.EpisodesAdapter
import com.hamza.movieapp.data.viewmodels.GetDetailsViewModel
import com.hamza.movieapp.databinding.LayoutEposidesBottomSheetBinding
import com.hamza.movieapp.ui.details.DetailsFragmentArgs
import com.hamza.movieapp.utils.ProgressLoading

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LayoutEposidesBottomSheet : BottomSheetDialogFragment() {

    private var _binding: LayoutEposidesBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var id: String? = null

    private val eposidesAdapter = EpisodesAdapter()
    private val viewModel: GetDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_eposides_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LayoutEposidesBottomSheetBinding.bind(view)
        id = DetailsFragmentArgs.fromBundle(requireArguments()).id
        actions()
        observe()

    }

    private fun observe() {
        viewModel.getDetails(id!!)
        viewModel.detailsLiveData.observe(viewLifecycleOwner) {
            binding.apply {
                it?.tvShow?.apply {
                    eposidesAdapter.list = episodes
                    binding.eposideRV.adapter = eposidesAdapter
                    binding.textTitle.text = it.tvShow.name
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun actions() {
        binding.close.setOnClickListener {
            findNavController().navigate(
                LayoutEposidesBottomSheetDirections.actionLayoutEposidesBottomSheetToDetailsFragment(
                    id.toString()
                )
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}