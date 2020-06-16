package com.android.boilerplate.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    fun loadImage(
        imageView: ImageView, url: String?, @DrawableRes resourceId: Int,
        circle: Boolean = false
    ) {
        if (circle) {
            Glide.with(imageView.context)
                .load(url)
                .placeholder(resourceId)
                .apply(RequestOptions().circleCrop())
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .placeholder(resourceId)
                .into(imageView)
        }
    }
}