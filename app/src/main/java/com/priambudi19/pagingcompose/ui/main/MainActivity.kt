package com.priambudi19.pagingcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.priambudi19.pagingcompose.ui.component.MyAppBar
import com.priambudi19.pagingcompose.ui.navigation.DetailScreen
import com.priambudi19.pagingcompose.ui.navigation.MainScreen
import com.priambudi19.pagingcompose.ui.navigation.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(Color.White, true)
            }
            Scaffold(
                topBar = {
                    MyAppBar(navController)
                }, content = {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                        composable(route = Screen.DetailScreen.route + "/{id}", arguments = listOf(
                            navArgument("id") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )) {
                            DetailScreen(id = it.arguments?.getString("id"),this@MainActivity)
                        }
                    }
                }
            )
        }
    }


}








