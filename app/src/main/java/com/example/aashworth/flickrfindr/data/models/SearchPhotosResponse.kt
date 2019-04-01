package com.example.aashworth.flickrfindr.data.models

import com.google.gson.annotations.SerializedName

data class SearchPhotosResponse(
        @SerializedName("photos") val photos: PhotosList
) {}


data class PhotosList (
        @SerializedName("photo") val photosList: List<Photo>
)

data class Photo(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String
) {}