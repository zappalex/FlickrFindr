package com.example.aashworth.flickrfindr.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aashworth.flickrfindr.InjectorUtils
import com.example.aashworth.flickrfindr.R
import com.example.aashworth.flickrfindr.data.PhotosRepository
import com.example.aashworth.flickrfindr.network.AppApiClient
import kotlinx.coroutines.*
import retrofit2.HttpException


class MainActivity : AppCompatActivity(), PhotoSearchFragment.SearchFragmentListener, PhotoDetailFragment.DetailFragmentListener {

    val photoSearchFragment = PhotoSearchFragment()
    val photoDetailFragment = PhotoDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoSearchFragment.photoSelectedListener = this
        photoDetailFragment.photoDetailListener = this
        addPhotoSearchFragment()

        // TODO: remove after testing
        val repository = InjectorUtils.getRepository()
        var myJob : Job? = null
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getPhotosForSearchTerm("yeti").await()
            withContext(Dispatchers.Main) {
                for (photo in result.photos.photosList){
                    Log.d("Heres one-> ", photo.id)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
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

    override fun onNavigateBack() {
        onBackPressed()
    }

}
