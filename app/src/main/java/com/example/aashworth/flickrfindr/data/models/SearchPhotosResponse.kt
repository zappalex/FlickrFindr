package com.example.aashworth.flickrfindr.data.models

import com.google.gson.annotations.SerializedName

data class SearchPhotosResponse(
        @SerializedName("photos") val photos: PhotosList
)

