package com.priambudi19.pagingcompose.data.network


import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.RequestFactory
import com.github.kittinunf.result.Result
import com.priambudi19.pagingcompose.data.model.PicsumPhotos

class PicsumClient : PicsumService {
    private fun request(api: RequestFactory.RequestConvertible): Request {
        return Fuel.request(api).timeout(30000)
    }

    override fun getListPhotos(page: Int): Result<List<PicsumPhotos>, FuelError> {
        val (_, _, result) = request(PicsumApi.ListPhoto(page)).responseObject(PicsumPhotos.DeserializeList)
        return result
    }

    override fun getPhotos(id: Int): Result<PicsumPhotos, FuelError> {
        val (_, _, result) = request(PicsumApi.Photo(id)).responseObject(PicsumPhotos.Deserialize)
        return result
    }
}