package com.priambudi19.pagingcompose.data.model


import com.google.gson.annotations.SerializedName

data class PicsumPhotos(
    @SerializedName("author")
    var author: String = "",
    @SerializedName("download_url")
    var downloadUrl: String = "",
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("id")
    var id: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("width")
    var width: Int = 0
)

typealias PicsumResponse = ArrayList<PicsumPhotos>