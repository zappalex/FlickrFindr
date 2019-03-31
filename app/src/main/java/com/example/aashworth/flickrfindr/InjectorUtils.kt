package com.example.aashworth.flickrfindr

import com.example.aashworth.flickrfindr.network.FlickrPhotoService
import com.example.aashworth.flickrfindr.network.ServiceComponent

object InjectorUtils {

    fun getFlickrPhotoService() : FlickrPhotoService {
        val interceptor = ServiceComponent.getFlickrInterceptor()
        val client = ServiceComponent.getClientWithInterceptor(interceptor)
        val retrofit = ServiceComponent.getRetrofit(ServiceComponent.FLICKR_BASE_URL, client)
        return ServiceComponent.getFlickrPhotoService(retrofit)
    }

}