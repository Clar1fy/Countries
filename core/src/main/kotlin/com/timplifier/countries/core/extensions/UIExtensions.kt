package com.timplifier.countries.core.extensions

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView

fun ImageView.rotateAnimate(
    fromDegrees: Float = 0F,
    toDegrees: Float = 90F,
    animationDuration: Long = 400
) {
    RotateAnimation(
        fromDegrees,
        toDegrees,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    ).apply {
        duration = animationDuration
        fillAfter = true
        startAnimation(this)
    }
}