package com.priambudi19.pagingcompose.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.priambudi19.pagingcompose.R
import com.priambudi19.pagingcompose.data.model.PicsumPhotos
import com.priambudi19.pagingcompose.util.PicsumUrl
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val photos = viewModel.getPhotos().collectAsLazyPagingItems()
            SideEffect {
                systemUiController.setStatusBarColor(Color.White,true)
            }
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Row {
                            Text(
                                text = "Paging",
                                color = Color.Black,
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontFamily = FontFamily(Font(R.font.rubik_regular))

                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Compose",
                                color = colorResource(id = R.color.DOT),
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontFamily = FontFamily(Font(R.font.rubik_regular))

                            )
                        }
                    }, backgroundColor = Color.White, elevation = 8.dp)
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    items(photos) { p ->
                        if (p != null) {
                            PhotosItem(photos = p)
                        }
                    }
                    photos.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {
                                    LoadingView(Modifier.fillParentMaxSize())
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                item {
                                    ErrorView(modifier = Modifier.fillParentMaxSize()) {
                                        retry()
                                    }
                                    Toast.makeText(
                                        this@MainActivity,
                                        (loadState.refresh as LoadState.Error).error.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                item {
                                    LoadingIndicator()
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                item {
                                    Retry {
                                        retry()
                                    }
                                    Toast.makeText(
                                        this@MainActivity,
                                        (loadState.append as LoadState.Error).error.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }

                }
            }
        }
    }

}


@Composable
fun PhotosItem(photos: PicsumPhotos) {
    Surface(
        modifier = Modifier
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

@Composable
@Preview(showBackground = true)
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Text(
                        text = "Paging",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontFamily = FontFamily(Font(R.font.rubik_regular))

                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Compose",
                        color = colorResource(id = R.color.DOT),
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontFamily = FontFamily(Font(R.font.rubik_regular))

                    )
                }
            }, backgroundColor = Color.White, elevation = 8.dp)
        }
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            PhotosItemPreview()
            PhotosItemPreview()
            PhotosItemPreview()
            PhotosItemPreview()
        }

    }
}

@Composable
@Preview
fun PhotosItemPreview() {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .height(250.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.sample_item),
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
                text = "Title",
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

@Composable
fun ErrorView(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Something went wrong",
                color = Color.Gray,
                fontSize = 18.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = onClick) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun LoadingView(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun Retry(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Something went wrong",
                color = Color.Gray,
                fontSize = 18.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = onClick) {
                Text(text = "Retry")
            }
        }
    }
}