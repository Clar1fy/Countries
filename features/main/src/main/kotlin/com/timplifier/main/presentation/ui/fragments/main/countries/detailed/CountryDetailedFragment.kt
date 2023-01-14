package com.timplifier.main.presentation.ui.fragments.main.countries.detailed

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.timplifier.countries.core.base.BaseFragment
import com.timplifier.countries.core.extensions.bindToUIStateLoading
import com.timplifier.countries.core.extensions.loadSvgImage
import com.timplifier.countries.core.extensions.visible
import com.timplifier.main.R
import com.timplifier.main.databinding.FragmentCountryDetailedBinding
import com.timplifier.main.presentation.ui.adapters.CountryCurrenciesAdapter
import com.timplifier.main.presentation.ui.adapters.CountryTimeZonesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailedFragment :
    BaseFragment<FragmentCountryDetailedBinding, CountryDetailedViewModel>(R.layout.fragment_country_detailed) {
    override val binding by viewBinding(FragmentCountryDetailedBinding::bind)
    override val viewModel by viewModels<CountryDetailedViewModel>()
    private val countryTimeZonesAdapter = CountryTimeZonesAdapter()
    private val countryCurrenciesAdapter = CountryCurrenciesAdapter()
    private val args by navArgs<CountryDetailedFragmentArgs>()
    override fun initialize() {
        constructTimeZonesRecycler()
        constructCurrenciesRecycler()
    }

    private fun constructTimeZonesRecycler() {
        binding.rvTimeZones.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTimeZones.adapter = countryTimeZonesAdapter
    }

    private fun constructCurrenciesRecycler() {
        binding.rvCurrencies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrencies.adapter = countryCurrenciesAdapter
    }

    override fun assembleViews() {
        renderToolbar()
    }

    private fun renderToolbar() {
        binding.toolbar.mtCountries.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
    }

    override fun constructListeners() {
        navigateBack()
    }

    private fun navigateBack() {
        binding.toolbar.mtCountries.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun establishRequest() {
        viewModel.fetchCountryByName(args.countryName)
    }

    override fun launchObservers() {
        subscribeToCountry()
    }

    private fun subscribeToCountry() = with(binding) {
        viewModel.country.spectateUiState(success = {
            it.apply {
                toolbar.mtCountries.title = name
                imCountryFlag.loadSvgImage(flags.svg)
                tvCountryName.text = name
                tvCountryCapital.text = capital
                tvCountryRegion.text = region
                countryTimeZonesAdapter.submitList(timezones)
                countryCurrenciesAdapter.submitList(currencies)
            }
            clCountryInfo.visible()
        }, gatherIfSucceed = {
            sflCountryInfo.bindToUIStateLoading(it)
        })
    }
}