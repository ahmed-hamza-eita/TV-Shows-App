package com.hamza.movieapp.ui.details;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.hamza.movieapp.R
import com.hamza.movieapp.adapters.ImageSliderAdapter
import com.hamza.movieapp.data.viewmodels.GetDetailsViewModel
import com.hamza.movieapp.databinding.DetailsFragmentBinding
import com.hamza.movieapp.utils.BaseFragment
import com.hamza.movieapp.utils.ProgressLoading
import com.hamza.movieapp.utils.showToast
import com.hamza.movieapp.utils.visibilityVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    // lateinit var binding: DetailsFragmentBinding
    private val viewModel: GetDetailsViewModel by viewModels()
    private var id: String? = null
    private val imageSliderAdapter = ImageSliderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
//        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding = DetailsFragmentBinding.bind(view)
        _binding = DetailsFragmentBinding.bind(view)
        id = DetailsFragmentArgs.fromBundle(requireArguments()).id


        observe()
    }

    private fun observe() {
        ProgressLoading.show(myActivity!!)
        viewModel.getDetails(id!!)
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            ProgressLoading.dismiss()
            showToast(it)

        }
        viewModel.detailsLiveData.observe(viewLifecycleOwner) {
            ProgressLoading.dismiss()
            imageSliderAdapter.sliderImage = it?.tvShow?.pictures
            binding.sliderViewPager.adapter = imageSliderAdapter
            binding.apply {
                it?.tvShow?.apply {
                    Glide.with(myContext!!).load(imageThumbnailPath).into(tvImg)
                    txtName.text = name
                    txtNetwork.text  = network
                    txtStarted.text  = startDate
                    txtStatus.text  = status

                }
            }

            //Managing SliderImage(ViewPager)
            binding.sliderViewPager.offscreenPageLimit = 1
            setupSliderIndicators(((imageSliderAdapter.itemCount) / 2))
            binding.sliderViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentSliderIndicator(position)
                }
            })

        }
    }

    private fun setupSliderIndicators(count: Int) {
        val indicators = arrayOfNulls<ImageView>(count)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(myContext)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    myContext!!,
                    R.drawable.background_slider_indicator_inactive
                )
            )
            indicators[i]?.layoutParams = layoutParams
            binding.layoutSliderIndicator.addView(indicators[i])
            binding.layoutSliderIndicator.visibilityVisible()
            setupSliderIndicators(0)
        }
    }

    private fun setCurrentSliderIndicator(position: Int) {
        val childCount = binding.layoutSliderIndicator.childCount
        for (i in 0 until childCount) {
            val imageView = binding.layoutSliderIndicator.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        myContext!!,
                        R.drawable.background_slider_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        myContext!!,
                        R.drawable.background_slider_indicator_inactive
                    )
                )
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}