package com.timplifier.countries.core.extensions

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

fun Fragment.overrideOnBackPressed(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        onBackPressed()
    }
}