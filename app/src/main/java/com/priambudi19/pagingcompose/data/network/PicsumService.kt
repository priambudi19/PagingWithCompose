package com.priambudi19.pagingcompose.data.network

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.priambudi19.pagingcompose.data.model.PicsumPhotos

interface PicsumService {
    fun getListPhotos(page: Int): Result<List<PicsumPhotos>, FuelError>
    fun getPhotos(id: Int):Result<PicsumPhotos, FuelError>
}