package com.example.aashworth.flickrfindr.data

import com.example.aashworth.flickrfindr.data.models.SearchPhotosResponse
import com.example.aashworth.flickrfindr.network.AppApiClient
import kotlinx.coroutines.Deferred

class PhotosRepository(private val appApiClient: AppApiClient) {

    fun getPhotosForSearchTerm(searchTerm: String): Deferred<SearchPhotosResponse> {
        val result = appApiClient.searchPhotos(searchTerm)
        return result
    }

    companion object {
        @Volatile
        private var instance: PhotosRepository? = null

        fun getInstance(appApiClient: AppApiClient) = instance ?: synchronized(this) {
            instance ?: PhotosRepository(appApiClient).also { instance = it }
        }
    }
}
