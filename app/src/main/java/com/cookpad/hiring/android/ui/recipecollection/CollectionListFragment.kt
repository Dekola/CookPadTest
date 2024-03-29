package com.cookpad.hiring.android.ui.recipecollection

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookpad.hiring.android.R
import com.cookpad.hiring.android.data.entities.Collection
import com.cookpad.hiring.android.databinding.FragmentCollectionListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionListFragment : Fragment(R.layout.fragment_collection_list) {

    private lateinit var collectionListAdapter: CollectionListAdapter
    private val viewModel: CollectionListViewModel by viewModels()

    private var _binding: FragmentCollectionListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCollectionListBinding.bind(view)

        setViews()
        setUpRecyclerView()

        binding.swipeToRefresh.apply {
            setOnRefreshListener {
                // Reset collectionOptionSwitch back to All collections because swipeToRefresh fetches all Collections
                binding.collectionOptionSwitch.isChecked = false

                isRefreshing = false
                viewModel.refresh()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when (viewState) {
                        is CollectionListViewState.Success -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            collectionListAdapter.submitList(viewState.collection)
                        }
                        CollectionListViewState.Error -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                R.string.generic_error_message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        CollectionListViewState.Loading -> {
                            binding.loadingCircularProgressIndicator.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun setViews() {
        with(binding){
            collectionOptionSwitch.setOnCheckedChangeListener { _, isChecked ->
                viewModel.toggleCollectionOption(isChecked)
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.collectionList.apply {
            collectionListAdapter = CollectionListAdapter(::favoriteSelection)
            adapter = collectionListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun favoriteSelection(collection: Collection){
        viewModel.favoriteSelection(collection)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}