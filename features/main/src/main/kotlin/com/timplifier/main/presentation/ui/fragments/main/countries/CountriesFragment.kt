package com.timplifier.main.presentation.ui.fragments.main.countries

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.timplifier.countries.core.base.BaseFragment
import com.timplifier.main.R
import com.timplifier.main.databinding.FragmentCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment :
    BaseFragment<FragmentCountriesBinding, CountriesViewModel>(R.layout.fragment_countries) {
    override val binding by viewBinding(FragmentCountriesBinding::bind)
    override val viewModel by viewModels<CountriesViewModel>()
}