package com.caiozed.videogamequiz.models.bindingAdaptersGameRequest

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.caiozed.videogamequiz.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageId: String?) {
        Picasso.get()
            .load("https://images.igdb.com/igdb/image/upload/t_720p/${imageId}.jpg")
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .into(view)
}