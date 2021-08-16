package com.priambudi19.pagingcompose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.priambudi19.pagingcompose.data.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    fun getPhotos() = repository.getListPhotos().cachedIn(viewModelScope)
}