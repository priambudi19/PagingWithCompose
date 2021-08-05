package com.priambudi19.pagingcompose.data.model


import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.core.ResponseDeserializable


data class PicsumPhotos(
    @Json("author")
    var author: String = "",
    @Json("download_url")
    var downloadUrl: String = "",
    @Json("height")
    var height: Int = 0,
    @Json("id")
    var id: String = "",
    @Json("url")
    var url: String = "",
    @Json("width")
    var width: Int = 0
) {

    object DeserializeList : ResponseDeserializable<List<PicsumPhotos>> {
        override fun deserialize(content: String): List<PicsumPhotos>? {
            return Klaxon().parseArray(content)
        }
    }

    object Deserialize : ResponseDeserializable<PicsumPhotos> {
        override fun deserialize(content: String): PicsumPhotos? {
            return Klaxon().parse(content)
        }
    }

}




