package com.priambudi19.pagingcompose.ui.info

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
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
                        text = "Info", color = Color.Black, style = TextStyle(
                            fontSize = 18.sp,
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

                    ) {
                        AndroidView(modifier = Modifier.size(200.dp),
                            factory = { context ->

                                val image = ImageView(context).apply {
                                    setImageResource(R.drawable.sample_item)
                                }
                                val text = TextView(context).apply {
                                    text = "This is android view"
                                }
                                LinearLayout(context).apply {
                                    orientation = LinearLayout.VERTICAL
                                    addView(text)
                                    addView(image)
                                }
                            },
                        )
                        Text(
                            text = "This is from ComposeView",
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                        Text(
                            text = "with Interoperability feature😜",
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                    }
                }


            }
        }
    }
}
