package com.example.aashworth.flickrfindr.presentation.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.aashworth.flickrfindr.data.PhotosRepository
import com.example.aashworth.flickrfindr.data.models.Photo

class PhotoSearchViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    var photosList = MutableLiveData<List<Photo>>()

    fun getPhotosListForTerm(term: String): LiveData<List<Photo>> {
        photosList = photosRepository.getPhotosForSearchTerm(term)
        return photosList
    }
}