/*
 * Copyright (c) 2020. Kunsang Wangyal
 */

package com.example.diyainfoapp.extension

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.diyainfoapp.R

fun View.onClickWithAnimation(callBack: () -> Unit) {
    this.setOnClickListener {
        val anim = AnimationUtils.loadAnimation(this.context, R.anim.timeline_bounce)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(p0: Animation?) {
                callBack()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                // platform callbacks : nothing to do anything here
            }

            override fun onAnimationStart(p0: Animation?) {
                // platform callbacks : nothing to do anything here
            }
        })
        this.startAnimation(anim)
    }
}
