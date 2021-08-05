package com.priambudi19.pagingcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.kittinunf.result.Result
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import com.priambudi19.pagingcompose.data.network.PicsumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class PicsumFuelPagingSource(private val picsumService: PicsumService) :
    PagingSource<Int, PicsumPhotos>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicsumPhotos> =
        withContext(Dispatchers.IO)
        {
            val page = params.key ?: 1

            try {
                when (val result = picsumService.getListPhotos(page)) {
                    is Result.Success -> {
                        val data = result.get()
                        val prev = if (page == 1) null else page - 1
                        val next = if (data.isEmpty()) null else page + 1
                        LoadResult.Page(
                            data = data,
                            prevKey = prev,
                            nextKey = next,
                        )
                    }
                    is Result.Failure -> {
                        result.error.exception.printStackTrace()
                        LoadResult.Error(result.error.exception)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
                LoadResult.Error(e)
            } catch (e: Exception) {
                e.printStackTrace()
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
