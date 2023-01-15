package com.timplifier.main.presentation.ui.fragments.main

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.timplifier.countries.core.base.BaseFlowFragment
import com.timplifier.countries.core.extensions.overrideOnBackPressed
import com.timplifier.countries.core.utils.InternetConnectivityManager
import com.timplifier.main.R
import com.timplifier.main.databinding.FragmentMainFlowBinding
import com.timplifier.main.presentation.ui.fragments.main.countries.CountriesViewModel

class MainFlowFragment :
    BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_fragment_main) {
    private val binding by viewBinding(FragmentMainFlowBinding::bind)
    private val viewModel by activityViewModels<CountriesViewModel>()
    private val internetConnectivityManager by lazy {
        InternetConnectivityManager(requireActivity())
    }

    override fun setupNavigation(navController: NavController) {
        subscribeToInternetConnectionStatus(navController)
        binding.iNoInternet.btnQuitTheApp.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun subscribeToInternetConnectionStatus(navController: NavController) = with(binding) {
        internetConnectivityManager.observe(viewLifecycleOwner) {
            iNoInternet.root.isGone = it
            navHostFragmentMain.isGone = !iNoInternet.root.isGone
            if (it) {
                navController.navigate(R.id.countriesFragment)
                viewModel.fetchCountries()
            }
            if (iNoInternet.root.isVisible)
                overrideOnBackPressed {
                    requireActivity().finish()
                }
        }
    }
}