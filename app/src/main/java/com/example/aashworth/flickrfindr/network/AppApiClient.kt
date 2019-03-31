package com.example.aashworth.flickrfindr.network

import com.example.aashworth.flickrfindr.InjectorUtils
import com.example.aashworth.flickrfindr.data.models.Photo
import retrofit2.Call
import javax.inject.Inject

object  AppApiClient {
    fun searchPhotos(searchTerm: String ) : Call<List<Photo>> {
        val flickrPhotoService = InjectorUtils.getFlickrPhotoService()
        return  flickrPhotoService.searchPhotos(searchTerm)
    }
}