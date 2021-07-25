package com.priambudi19.pagingcompose.util

import com.priambudi19.pagingcompose.data.model.PicsumPhotos

object PicsumUrl {
    private const val BASE_IMAGE = "https://picsum.photos/id/"
    fun getImage(photos: PicsumPhotos, width: Int = 400, height: Int = 300): String {
        return "$BASE_IMAGE${photos.id}/$width/$height"
    }
}