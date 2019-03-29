package com.example.aashworth.flickrfindr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), PhotoSearchFragment.OnPhotoSelectedListener {

    val photoSearchFragment = PhotoSearchFragment()
    val photoDetailFragment = PhotoDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoSearchFragment.photoSelectedListener = this
        addPhotoSearchFragment()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0 ){
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun addPhotoSearchFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, photoSearchFragment, "searchFragment")
                .commit()
    }

    private fun addPhotoDetailFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, photoDetailFragment, "detailFragment")
                .addToBackStack(null)
                .commit()
    }

    override fun onPhotoSelected() {
        addPhotoDetailFragment()
    }
}
