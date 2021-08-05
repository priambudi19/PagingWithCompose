package com.priambudi19.pagingcompose.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.priambudi19.pagingcompose.ui.component.*
import com.priambudi19.pagingcompose.ui.main.MainViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun MainScreen(navController: NavController) {
    val viewModel: MainViewModel = getViewModel()
    val photos =  viewModel.getPhotos().collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        items(photos) { p ->
            if (p != null) {
                PhotosItem(
                    photos = p,
                    onClick = { navController.navigate(route = Screen.DetailScreen.withArgs(p.id)) })
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
                            LocalContext.current,
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
                            LocalContext.current,
                            (loadState.append as LoadState.Error).error.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }
}


