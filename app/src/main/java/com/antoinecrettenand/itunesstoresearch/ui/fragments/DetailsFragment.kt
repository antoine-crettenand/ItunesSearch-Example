package com.antoinecrettenand.itunesstoresearch.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.antoinecrettenand.itunesstoresearch.R
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItem
import com.antoinecrettenand.itunesstoresearch.databinding.FragmentDetailsBinding
import com.antoinecrettenand.itunesstoresearch.ui.viewmodels.MainViewModel
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.selectedItunesItem.observe(viewLifecycleOwner) { itunesItem ->

            if (itunesItem != null) {
                val scaledPreviewImage = itunesItem.artworkUrl100?.replace("100", "400")

                scaledPreviewImage?.let {
                    Glide.with(this)
                        .load(it)
                        .centerInside()
                        .into(binding.detailsPreviewImage)
                }

                binding.detailsTrackName.text = itunesItem.trackName
                binding.detailsArtistName.text = itunesItem.artistName
                binding.detailsDescription.text =
                    itunesItem.getReadableLongDescription(error = getString(R.string.unavailable_description))

                if (itunesItem.trackPrice != null && itunesItem.trackPrice.isFinite()) {
                    binding.detailsTrackPrice.text =
                        getString(R.string.price_and_currency, itunesItem.trackPrice, itunesItem.currency)
                }

                if (!itunesItem.trackViewUrl.isNullOrBlank())
                    binding.detailsTrackPrice.setOnClickListener {
                        val openUrl = Intent(Intent.ACTION_VIEW)
                        openUrl.data = Uri.parse(itunesItem.trackViewUrl)
                        startActivity(openUrl)
                    }
                else
                    binding.detailsTrackPrice.text = getString(R.string.unknown_track_price_event)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
