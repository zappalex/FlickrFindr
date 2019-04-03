package com.example.aashworth.flickrfindr.network

import android.widget.ImageView
import com.example.aashworth.flickrfindr.data.models.Photo
import com.squareup.picasso.Picasso

object ImageDownloader {

    fun downloadMediumImage(photo: Photo, view:ImageView ) {
        val path = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_n.jpg"
            Picasso.get()
                    .load(path)
                    .fit()
                    .centerCrop()
                    .into(view)
    }

    fun downloadLargeImage(photo: Photo, view:ImageView ) {
        val path = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg"
        Picasso.get()
                .load(path)
                .fit()
                .centerCrop()
                .into(view)
    }
}