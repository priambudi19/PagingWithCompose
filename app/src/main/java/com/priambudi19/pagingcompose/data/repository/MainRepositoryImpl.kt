package com.priambudi19.pagingcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import com.priambudi19.pagingcompose.data.network.ApiService
import com.priambudi19.pagingcompose.paging.PicsumPagingSource
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {
    override fun getListPhotos(): Flow<PagingData<PicsumPhotos>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 3),
            pagingSourceFactory = {
                PicsumPagingSource(apiService = apiService)

            }).flow
    }
}