package com.example.aashworth.flickrfindr.data.models

import com.google.gson.annotations.SerializedName

data class PhotosList(
        @SerializedName("photo") val photosList: List<Photo>
)