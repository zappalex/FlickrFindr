package com.example.aashworth.flickrfindr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.photo_search_fragment.view.*

class PhotoSearchFragment: Fragment() {

    var photoSelectedListener: OnPhotoSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.photo_search_fragment, container, false)

        view.test_button.setOnClickListener{
            photoSelectedListener?.onPhotoSelected()
        }

        return view
    }

    interface OnPhotoSelectedListener {
        fun onPhotoSelected()
    }
}