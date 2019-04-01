package com.example.aashworth.flickrfindr.network

import com.example.aashworth.flickrfindr.data.models.SearchPhotosResponse
import kotlinx.coroutines.Deferred

class AppApiClient (private val photoService: FlickrPhotoService) {

    fun searchPhotos(searchTerm: String): Deferred<SearchPhotosResponse> {
        return photoService.searchPhotos(searchTerm)
    }

    companion object {
        @Volatile private var instance: AppApiClient? = null

        fun getInstance(photoService: FlickrPhotoService) = instance ?: synchronized(this) {
            instance ?: AppApiClient(photoService).also { instance = it }
        }
    }
}