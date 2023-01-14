package com.timplifier.countries.core.extensions

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

fun ImageView.loadImageWithGlide(url: String) {
    ImageLoader.Builder(context).components(fun ComponentRegistry.Builder.() {
        add(SvgDecoder.Factory())
    }).build().enqueue(
        ImageRequest.Builder(context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
    )
}