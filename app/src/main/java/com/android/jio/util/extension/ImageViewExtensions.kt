package com.android.jio.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


/**
 * Loads image from the url.
 * @param imgUrl string url
 */
fun ImageView.loadImage(imgUrl: String? = null) {
    Glide.with(this)
        .load(imgUrl)
        .placeholder(android.R.color.darker_gray)
        .error(android.R.color.holo_red_light)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}
