package com.example.aashworth.flickrfindr.network

import com.example.aashworth.flickrfindr.data.models.SearchPhotosResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotoService {
    @GET(".")
    fun searchPhotos(@Query("text") searchTerm: String?)
            : Deferred<SearchPhotosResponse>
}
