package com.priambudi19.pagingcompose.ui.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.priambudi19.pagingcompose.ui.component.ErrorView
import com.priambudi19.pagingcompose.ui.component.PhotosItem
import com.priambudi19.pagingcompose.ui.detail.DetailViewModel
import com.priambudi19.pagingcompose.util.Resource
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(id: String?, context: Context) {
    val viewModel: DetailViewModel = getViewModel()
    val photo by remember { viewModel.getDetail(id!!.toInt()) }.collectAsState(Resource.Loading())
    Box(modifier = Modifier.fillMaxSize()) {
        when (val resource = photo) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is Resource.Error -> {
                ErrorView() {}
            }
            is Resource.Success -> {
                resource.data.let {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        PhotosItem(photos = it!!)
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Source :",
                            textAlign = TextAlign.Center
                        )
                        TextButton(onClick = {
                            val i = Intent(Intent.ACTION_VIEW)
                            i.setData(Uri.parse(it.url))
                            context.startActivity(i)
                        }) {
                            Text(text = it.url, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}
