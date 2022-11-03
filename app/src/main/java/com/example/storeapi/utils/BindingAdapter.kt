package com.example.storeapi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.storeapi.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(imgView)
    }
}