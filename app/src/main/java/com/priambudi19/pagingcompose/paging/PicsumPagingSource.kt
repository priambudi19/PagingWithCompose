package com.priambudi19.pagingcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import com.priambudi19.pagingcompose.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class PicsumPagingSource(private val apiService: ApiService) : PagingSource<Int, PicsumPhotos>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicsumPhotos> {
        return try {
            val page = params.key ?: 1
            val data = apiService.getPhotos(page)
            val prev = if (page == 1) null else page - 1
            val next = if (data.isEmpty()) null else page + 1
            LoadResult.Page(
                data = data,
                prevKey = prev,
                nextKey = next,
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PicsumPhotos>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
