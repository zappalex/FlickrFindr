package com.example.aashworth.flickrfindr.network

import android.arch.lifecycle.LiveData
import com.example.aashworth.flickrfindr.InjectorUtils
import com.example.aashworth.flickrfindr.data.models.SearchPhotosResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response


object AppApiClient {
    fun searchPhotos(searchTerm: String): Deferred<SearchPhotosResponse> {
        val flickrPhotoService = InjectorUtils.getFlickrPhotoService()
        return flickrPhotoService.searchPhotos(searchTerm)
    }
}