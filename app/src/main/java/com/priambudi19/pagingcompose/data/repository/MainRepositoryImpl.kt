package com.priambudi19.pagingcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.kittinunf.result.Result
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import com.priambudi19.pagingcompose.data.network.PicsumService
import com.priambudi19.pagingcompose.paging.PicsumPagingSource
import com.priambudi19.pagingcompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepositoryImpl(private val picsumService: PicsumService) : MainRepository {

    override fun getListPhotos(): Flow<PagingData<PicsumPhotos>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 3),
            pagingSourceFactory = {
                PicsumPagingSource(picsumService)

            }).flow
    }

    override fun getDetail(id: Int) = flow<Resource<PicsumPhotos>> {
        when (val result = picsumService.getPhotos(id)) {
            is Result.Success -> {
                emit(Resource.Success(result.value))
            }
            is Result.Failure -> {
                emit(Resource.Error(result.error.message))
            }
        }
    }.flowOn(Dispatchers.IO)


}