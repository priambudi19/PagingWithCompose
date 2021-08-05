package com.priambudi19.pagingcompose.ui.navigation

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    fun withArgs(vararg args:String): String = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}
