package com.priambudi19.pagingcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.priambudi19.pagingcompose.R
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import com.priambudi19.pagingcompose.util.PicsumUrl

@Composable
fun PhotosItem(photos: PicsumPhotos, onClick: ((PicsumPhotos) -> Unit)? = null) {
    Surface(
        modifier = Modifier
            .clickable { onClick?.invoke(photos) }
            .requiredHeight(250.dp)
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                painter = rememberImagePainter(
                    data = PicsumUrl.getImage(photos),
                    builder = {
                        crossfade(1000)
                        placeholder(R.drawable.ic_img_loading)
                        error(R.drawable.ic_img_error)
                    }
                ),
                contentScale = ContentScale.Crop, contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            ) {}
            Text(
                text = photos.author,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(), maxLines = 1
            )
        }

    }
}