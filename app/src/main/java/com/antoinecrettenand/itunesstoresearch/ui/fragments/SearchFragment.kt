package com.antoinecrettenand.itunesstoresearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoinecrettenand.itunesstoresearch.R
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItem
import com.antoinecrettenand.itunesstoresearch.databinding.FragmentSearchBinding
import com.antoinecrettenand.itunesstoresearch.ui.adapters.ItunesItemAdapter
import com.antoinecrettenand.itunesstoresearch.ui.adapters.RecyclerTouchListener
import com.antoinecrettenand.itunesstoresearch.ui.viewmodels.MainViewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        navController = findNavController(requireActivity(), R.id.nav_host_fragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        mainViewModel.itunesItemList.observe(viewLifecycleOwner) {
            val adapter = binding.searchResults.adapter as ItunesItemAdapter
            adapter.submitList(it)
        }

        mainViewModel.hasResults.observe(viewLifecycleOwner) {
            if (!it) Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        val recyclerTouchListener: RecyclerTouchListener = object : RecyclerTouchListener() {
            override fun onItemClick(itunesItem: ItunesItem) {
                mainViewModel.selectItunesItem(itunesItem)
                navController.navigate(R.id.action_searchFragment_to_detailsFragment)
            }
        }
        val adapter = ItunesItemAdapter(requireContext(), recyclerTouchListener)
        binding.searchResults.adapter = adapter
        binding.searchResults.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
