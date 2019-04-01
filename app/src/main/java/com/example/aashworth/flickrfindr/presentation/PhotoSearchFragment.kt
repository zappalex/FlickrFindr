package com.example.aashworth.flickrfindr.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aashworth.flickrfindr.InjectorUtils
import com.example.aashworth.flickrfindr.R
import com.example.aashworth.flickrfindr.data.PhotosRepository
import com.example.aashworth.flickrfindr.network.FlickrPhotoService

import kotlinx.android.synthetic.main.photo_search_fragment.view.*

class PhotoSearchFragment: Fragment() {

    var photoSelectedListener: SearchFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.photo_search_fragment, container, false)

        view.test_button.setOnClickListener{
            photoSelectedListener?.onPhotoSelected()
        }

        return view
    }

    interface SearchFragmentListener {
        fun onPhotoSelected()
    }
}