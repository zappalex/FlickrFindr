package com.example.aashworth.flickrfindr.network

import com.example.aashworth.flickrfindr.data.models.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotoService {
    @GET()
    fun searchPhotos(@Query("text") searchTerm: String,
                     @Query("method") method: String = "flickr.photos.search")
            : Call<List<Photo>>

}
