package com.priambudi19.pagingcompose.ui.component

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.priambudi19.pagingcompose.R
import com.priambudi19.pagingcompose.ui.info.InfoActivity
import com.priambudi19.pagingcompose.ui.navigation.Screen

@Composable
fun MyAppBar(navController: NavController) {
    val context = LocalContext.current.applicationContext
    TopAppBar(
        title = {
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
        }, backgroundColor = Color.White, elevation = 8.dp, actions = {
            IconButton(onClick = {
                val intent = Intent(context, InfoActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_info_24),
                    contentDescription = ""
                )
            }
        }, navigationIcon = {
            val backState = navController.currentBackStackEntryAsState()
            val route = backState.value?.destination?.route
            if (route!=null){
                if (route != Screen.MainScreen.route) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                } else {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_border_horizontal_24),
                            contentDescription = "", tint = colorResource(id = R.color.DOT)
                        )
                    }
                }
            }

        })
}