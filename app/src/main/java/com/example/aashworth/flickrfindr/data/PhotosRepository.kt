package com.example.aashworth.flickrfindr.data

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.aashworth.flickrfindr.data.models.Photo
import com.example.aashworth.flickrfindr.network.AppApiClient
import kotlinx.coroutines.*

class PhotosRepository(private val appApiClient: AppApiClient) {
    val TAG = "PhotosRepository";

    fun getPhotosForSearchTerm(searchTerm: String): MutableLiveData<List<Photo>> {
        var photosLiveData = MutableLiveData<List<Photo>>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = appApiClient.searchPhotos(searchTerm).await()
                withContext(Dispatchers.Main) {
                    photosLiveData.value = result.photos.photosList
                }
            }catch (e: Throwable) {
                Log.e(TAG, "Error executing search request")
            }
        }
        return photosLiveData
    }

    companion object {
        @Volatile
        private var instance: PhotosRepository? = null

        fun getInstance(appApiClient: AppApiClient) = instance ?: synchronized(this) {
            instance ?: PhotosRepository(appApiClient).also { instance = it }
        }
    }
}
