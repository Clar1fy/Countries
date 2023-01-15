package com.timplifier.main.presentation.ui.fragments.main.countries

import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.timplifier.countries.core.base.BaseFragment
import com.timplifier.countries.core.extensions.*
import com.timplifier.main.R
import com.timplifier.main.databinding.FragmentCountriesBinding
import com.timplifier.main.presentation.ui.adapters.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountriesFragment :
    BaseFragment<FragmentCountriesBinding, CountriesViewModel>(R.layout.fragment_countries) {
    override val binding by viewBinding(FragmentCountriesBinding::bind)
    override val viewModel by activityViewModels<CountriesViewModel>()
    private val countriesAdapter = CountriesAdapter(this::onItemClick)

    override fun initialize() {
        constructAdapter()
    }

    private fun constructAdapter() = with(binding) {
        rvCountries.adapter = countriesAdapter
        rvCountries.layoutManager = LinearLayoutManager(requireContext())
        countriesAdapter.bindViewsToPagingLoadStates(
            binding.rvCountries,
            shimmerFrameLayout = sflCountries,
            smartRefreshLayout = srlCountries
        )
    }

    override fun assembleViews() {
        setToolbarTitle()
        modifySearchView()
    }

    private fun setToolbarTitle() {
        binding.toolbar.mtCountries.title = getString(R.string.countries)
    }

    private fun modifySearchView() {
        binding.toolbar.svCountries.visible()
        val searchView: EditText =
            binding.toolbar.svCountries.findViewById(androidx.appcompat.R.id.search_src_text)
        searchView.setHintTextColor(Color.WHITE)
        searchView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        val searchClearButton: ImageView =
            binding.toolbar.svCountries.findViewById(androidx.appcompat.R.id.search_close_btn)
        searchClearButton.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        searchClearButton.setImageResource(R.drawable.ic_cancel)
    }

    override fun constructListeners() {
        establishSearch()
        handleSwipeToRefresh()
        refreshCountriesByClickingOnButton()
        renderViewsVisibilityWhenSearchViewIsFocused()
    }

    private fun establishSearch() {
        binding.toolbar.svCountries.apply {
            setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.modifySearchQuery(it)
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.modifySearchQuery(it)
                        clearFocus()
                    }
                    return false

                }
            })
        }
    }

    private fun handleSwipeToRefresh() = with(binding) {
        srlCountries.setRefreshHeader(srlChCountries)
        srlCountries.setOnRefreshListener {
            postHandler(1500L) {
                refreshCountries()
                srlCountries.finishRefresh()
            }
        }
    }

    private fun refreshCountriesByClickingOnButton() = with(binding) {
        btnRefresh.setOnClickListener {
            cpiRefresh.visible()
            btnRefresh.text = ""
            postHandler(500L) {
                refreshCountries()
                cpiRefresh.invisible()
                btnRefresh.text = getString(R.string.refresh)
            }
        }
    }

    private fun renderViewsVisibilityWhenSearchViewIsFocused() {
        binding.toolbar.svCountries.apply {
            setOnQueryTextFocusChangeListener { _, hasFocus ->
                binding.toolbar.mtCountries.title = when (hasFocus) {
                    true -> ""
                    false -> {
                        onActionViewCollapsed()
                        getString(R.string.countries)
                    }
                }
            }
            setOnCloseListener {
                viewModel.modifySearchQuery("")
                setQuery("", true)
                binding.rvCountries.scrollToPosition(0)
                clearFocus()
                onActionViewCollapsed()
                false
            }
        }
    }

    override fun launchObservers() {
        subscribeToCountries()
        subscribeToSearchQuery()
    }

    private fun subscribeToCountries() = with(binding) {
        viewModel.countries.spectateUiState(success = {
            safeFlowGather {
                countriesAdapter.submitData(PagingData.from(it))
            }
        }, gatherIfSucceed = {
            btnRefresh.bindToUIStateNotLoading(it)
            sflCountries.bindToUIStateLoading(it)
        }, error = {
            loge(it)
        })
    }

    private fun subscribeToSearchQuery() {
        safeFlowGather {
            viewModel.searchQuery.collectLatest {
                it?.let {
                    viewModel.fetchCountriesByName(it)
                } ?: viewModel.fetchCountriesByName("")
            }
        }
    }

    private fun onItemClick(countryName: String) {
        findNavController().navigate(
            CountriesFragmentDirections.actionCountriesFragmentToCountryDetailedFragment(
                countryName
            )
        )
    }

    private fun refreshCountries() {
        safeFlowGather {
            countriesAdapter.submitData(PagingData.empty())
            viewModel.fetchCountries()
            subscribeToCountries()
        }
    }
}