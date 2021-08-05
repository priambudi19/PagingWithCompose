package com.priambudi19.pagingcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priambudi19.pagingcompose.R

@Composable
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

@Composable
fun ErrorView(modifier: Modifier = Modifier, onClick: () -> Unit) {
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