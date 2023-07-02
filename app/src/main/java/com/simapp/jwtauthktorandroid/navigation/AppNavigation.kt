package com.simapp.jwtauthktorandroid.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simapp.jwtauthktorandroid.ui.AuthScreen
import com.simapp.jwtauthktorandroid.ui.MainViewModel
import com.simapp.jwtauthktorandroid.ui.SecretScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()


    // val baseConfigViewModel: BaseConfigViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.AuthScreens.Route,
        route = "nav_root"
    ) {



            composable(Screens.AuthScreens.Route) {
                AuthScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }


        composable(Screens.SecretScreens.Route) {
            SecretScreen(

            )
        }

    }
}