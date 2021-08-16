package com.priambudi19.pagingcompose.ui.detail

import androidx.lifecycle.ViewModel
import com.priambudi19.pagingcompose.data.repository.MainRepository

class DetailViewModel(private val repository: MainRepository) : ViewModel() {
    fun getDetail(id: Int)  = repository.getDetail(id)
}