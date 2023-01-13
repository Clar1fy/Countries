package com.timplifier.countries.core.extensions

import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.timplifier.countries.core.ui.state.UIState

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun <T> ProgressBar.bindToUILoadingState(state: UIState<T>) {
    isVisible = state is UIState.Loading
}