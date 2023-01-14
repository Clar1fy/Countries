package com.timplifier.main.presentation.ui.fragments.main.countries

import androidx.fragment.app.viewModels
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

@AndroidEntryPoint
class CountriesFragment :
    BaseFragment<FragmentCountriesBinding, CountriesViewModel>(R.layout.fragment_countries) {
    override val binding by viewBinding(FragmentCountriesBinding::bind)
    override val viewModel by viewModels<CountriesViewModel>()
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
    }

    private fun setToolbarTitle() {
        binding.toolbar.mtCountries.title = getString(R.string.countries)
    }

    override fun constructListeners() {
        handleSwipeToRefresh()
        refreshCountriesByClickingOnButton()
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

    override fun launchObservers() {
        subscribeToCountries()
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