package com.priambudi19.pagingcompose.ui.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.priambudi19.pagingcompose.R


class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<ComposeView>(R.id.composeView).setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(Color.White, true)
            }
            Scaffold(topBar = {
                TopAppBar(backgroundColor = Color.White, title = {
                    Text(
                        text = "Info", color = Color.Black, style = TextStyle(fontSize = 18.sp,
                            fontFamily = FontFamily(
                                listOf(Font(R.font.rubik_regular))
                            )
                        )
                    )
                }, navigationIcon = {
                    IconButton(onClick = { this.finish() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                })
            }) {

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentSize()
                    ) {
                        Text(text = "This is from ComposeView",style = TextStyle(textAlign = TextAlign.Center))
                        Text(text = "with Interopability feature😜",style = TextStyle(textAlign = TextAlign.Center))
                    }
                }

            }
        }
    }
}