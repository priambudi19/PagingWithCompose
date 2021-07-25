package com.priambudi19.pagingcompose.data.network

import com.priambudi19.pagingcompose.data.model.PicsumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/list")
    suspend fun getPhotos(@Query("page") page: Int): PicsumResponse
}