package com.example.aashworth.flickrfindr.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.aashworth.flickrfindr.data.models.Photo
import com.example.aashworth.flickrfindr.network.AppApiClient
import kotlinx.coroutines.*

class PhotosRepository(private val appApiClient: AppApiClient) {

    fun getPhotosForSearchTerm(searchTerm: String): MutableLiveData<List<Photo>> {
        var photosLiveData = MutableLiveData<List<Photo>>()

        CoroutineScope(Dispatchers.IO).launch {
            val result = appApiClient.searchPhotos(searchTerm).await()
            withContext(Dispatchers.Main) {
                for (photo in result?.photos?.photosList) {
                    photo.fullPhotoUrl = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg"
                }
                photosLiveData.value = result.photos.photosList
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
