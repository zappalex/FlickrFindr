package com.example.aashworth.flickrfindr.presentation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aashworth.flickrfindr.R


class PhotoSearchFragment() : Fragment() {

    var photoSelectedListener: SearchFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.photo_search_fragment, container, false)
        return view
    }


    interface SearchFragmentListener {
        fun onPhotoSelected()
    }
}