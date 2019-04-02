package com.example.aashworth.flickrfindr.data.models

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("secret") val secret: String,
        @SerializedName("server") val server: String,
        @SerializedName("farm") val farm: String,
        var fullPhotoUrl: String
)