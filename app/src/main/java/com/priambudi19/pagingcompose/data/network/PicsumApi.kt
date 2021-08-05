package com.priambudi19.pagingcompose.data.network

import com.github.kittinunf.fuel.core.HeaderValues
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.util.FuelRouting

internal sealed class PicsumApi : FuelRouting {
    class ListPhoto(val page: Int) : PicsumApi()
    class Photo(val id: Int) : PicsumApi()

    override val basePath: String = "https://picsum.photos"
    override val method: Method
        get() = when (this) {
            is ListPhoto -> Method.GET
            is Photo -> Method.GET
        }
    override val path: String
        get() = when(this){
            is ListPhoto -> "/v2/list"
            is Photo -> "/id/$id/info"
        }

    override val params: Parameters?
        get() = when(this){
            is ListPhoto -> listOf("page" to page)
            else -> null
        }
    override val body: String?
        get() = null
    override val bytes: ByteArray?
        get() = null
    override val headers: Map<String, HeaderValues>?
        get() = null
}

