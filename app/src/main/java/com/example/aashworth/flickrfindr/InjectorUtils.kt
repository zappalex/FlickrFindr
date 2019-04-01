package com.example.aashworth.flickrfindr

import com.example.aashworth.flickrfindr.data.PhotosRepository
import com.example.aashworth.flickrfindr.network.AppApiClient
import com.example.aashworth.flickrfindr.network.FlickrPhotoService
import com.example.aashworth.flickrfindr.network.ServiceComponent

object InjectorUtils {

    fun getRepository() : PhotosRepository {
        val interceptor = ServiceComponent.getFlickrSearchPhotosInterceptor()
        val client = ServiceComponent.getClientWithInterceptor(interceptor)
        val retrofit = ServiceComponent.getRetrofit(ServiceComponent.FLICKR_BASE_URL, client)
        val photoService = ServiceComponent.getFlickrPhotoService(retrofit)
        val apiClient = AppApiClient.getInstance(photoService);
        return PhotosRepository.getInstance(apiClient)
    }

}