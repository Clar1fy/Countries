package com.timplifier.main.presentation.ui.fragments.main.countries

import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.timplifier.countries.core.base.BaseFragment
import com.timplifier.countries.core.extensions.*
import com.timplifier.main.R
import com.timplifier.main.databinding.FragmentCountriesBinding
import com.timplifier.main.presentation.models.CountryUI
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
        binding.rvCountries.adapter = countriesAdapter
        binding.rvCountries.layoutManager = LinearLayoutManager(requireContext())
        countriesAdapter.bindViewsToPagingLoadStates(
            binding.rvCountries,
            shimmerFrameLayout = binding.sflCountries,
            smartRefreshLayout = binding.srlCountries
        )
    }

    override fun constructListeners() {
        handleSwipeToRefresh()
        refreshCountriesByClickingOnButton()
    }

    private fun handleSwipeToRefresh() {
        binding.srlCountries.setRefreshHeader(binding.srlChCountries)
        binding.srlCountries.setOnRefreshListener {
            postHandler(1500L) {
                refreshCountries()
                binding.srlCountries.finishRefresh()
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
        })
    }

    private fun onItemClick(country: CountryUI) {

//        findNavController().navigate(R.id.)
    }

    private fun refreshCountries() {
        safeFlowGather {
            countriesAdapter.submitData(PagingData.empty())
            viewModel.fetchCountries()
            subscribeToCountries()
        }
    }
}