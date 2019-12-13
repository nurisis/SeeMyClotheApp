package com.nurisis.seemyclothappp.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imagePath")
fun loadImage(imageView: ImageView, @Nullable path:String?) {
    var myOptions = RequestOptions().centerCrop()

    Glide.with(imageView.context)
        .load(path ?: "")
        .apply(myOptions)
        .into(imageView)
}

@BindingAdapter("imageUri")
fun loadImageFromUri(imageView: ImageView, @Nullable uri:Uri) {
    var myOptions = RequestOptions().fitCenter()

    Glide.with(imageView.context)
        .load(uri)
        .apply(myOptions)
        .into(imageView)
}

@BindingConversion
fun convertBooleanToVisibility(visible:Boolean) :Int {
    return if(visible) View.VISIBLE else View.GONE
}
