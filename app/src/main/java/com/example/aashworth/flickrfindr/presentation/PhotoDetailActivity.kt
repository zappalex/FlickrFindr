package com.example.aashworth.flickrfindr.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.example.aashworth.flickrfindr.R
import com.example.aashworth.flickrfindr.data.models.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        retrieveIntents()
    }

    fun retrieveIntents() {
        val intentThatStartedActivity = intent
        if (intentThatStartedActivity.hasExtra(MainActivity.PARCELABLE_PHOTO)) {
            val currentPhoto = intentThatStartedActivity.getParcelableExtra(MainActivity.PARCELABLE_PHOTO) as Photo
            currentPhoto?.let { populateViews(it) }
        }
        // TODO: handle else
    }

    fun populateViews(photo: Photo) {

        photo_title_textview.setText(photo.title)

        Picasso.get()
                .load(photo.fullPhotoUrl)
                .fit()
                .centerCrop()
                .into(photo_imgview)

    }

}
