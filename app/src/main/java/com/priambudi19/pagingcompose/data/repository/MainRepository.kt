package com.priambudi19.pagingcompose.data.repository

import androidx.paging.PagingData
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getListPhotos() : Flow<PagingData<PicsumPhotos>>
}