package com.example.aashworth.flickrfindr.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.aashworth.flickrfindr.data.models.SearchPhotosResponse
import com.example.aashworth.flickrfindr.network.AppApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotosRepository(private val appApiClient: AppApiClient) {

//    fun getPhotosForSearchTerm(searchTerm: String): Deferred<SearchPhotosResponse> {
////        val photosCall = appApiClient.searchPhotos(searchTerm)
////        val data = MutableLiveData<SearchPhotosResponse>()
////
////        photosCall.enqueue(object: Callback<SearchPhotosResponse>{
////            override fun onFailure(call: Call<SearchPhotosResponse>?, t: Throwable?) {
////            }
////
////            override fun onResponse(call: Call<SearchPhotosResponse>?, response: Response<SearchPhotosResponse>?) {
////                data.value = response?.body()
////            }
////        })
////        return data
//
//        val result = appApiClient.searchPhotos(searchTerm)
//        return result
//    }

    companion object {
        @Volatile private var instance: PhotosRepository? = null

        fun getInstance(appApiClient: AppApiClient) = instance ?: synchronized(this) {
            instance ?: PhotosRepository(appApiClient).also { instance = it }
        }
    }
}
