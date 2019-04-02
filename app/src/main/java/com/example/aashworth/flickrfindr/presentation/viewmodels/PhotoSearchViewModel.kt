package com.example.aashworth.flickrfindr.presentation.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.aashworth.flickrfindr.data.PhotosRepository
import com.example.aashworth.flickrfindr.data.models.Photo
import kotlinx.coroutines.*

class PhotoSearchViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    fun getPhotosForSearchTerm(searchTerm: String): LiveData<List<Photo>> {
        var photosLiveData = MutableLiveData<List<Photo>>()

        //TODO: cancel job
        var myJob: Job? = null
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = photosRepository.getPhotosForSearchTerm(searchTerm).await()
            withContext(Dispatchers.Main) {
                for (photo in result?.photos?.photosList) {
                    photo.fullPhotoUrl = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
                }
                photosLiveData.value = result.photos.photosList
            }
        }
        return photosLiveData
    }

}