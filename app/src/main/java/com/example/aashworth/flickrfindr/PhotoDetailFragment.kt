package com.example.aashworth.flickrfindr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.photo_detail_fragment.view.*
import kotlinx.android.synthetic.main.photo_search_fragment.view.*

class PhotoDetailFragment: Fragment() {

    var photoDetailListener: DetailFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.photo_detail_fragment, container, false)

        view.navigate_back_button.setOnClickListener{ photoDetailListener?.onNavigateBack() }

        return view
    }

    interface DetailFragmentListener {
        fun onNavigateBack()
    }
}
