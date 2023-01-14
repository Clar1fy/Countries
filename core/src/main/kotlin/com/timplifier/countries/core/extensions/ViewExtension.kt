package com.timplifier.countries.core.extensions

import android.view.View
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout
import com.timplifier.countries.core.ui.state.UIState

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun makeMultipleViewsInvisible(vararg views: View) {
    views.forEach {
        it.invisible()
    }
}

fun <T> View.bindToUIStateLoading(uiState: UIState<T>) {
    isVisible = uiState is UIState.Loading
}

fun <T> View.bindToUIStateNotLoading(uiState: UIState<T>) {
    isVisible = uiState !is UIState.Loading
}

fun <T> ShimmerFrameLayout.bindToUIStateLoading(uiState: UIState<T>) {
    when (uiState) {
        is UIState.Loading -> {
            visible()
            showShimmer(true)
        }
        else -> {
            gone()
            stopShimmer()
            hideShimmer()
        }
    }
}