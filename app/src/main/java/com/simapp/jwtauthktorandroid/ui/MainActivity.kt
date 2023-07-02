package com.simapp.jwtauthktorandroid.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.simapp.jwtauthktorandroid.navigation.AppNavigation
import com.simapp.jwtauthktorandroid.ui.theme.JWTAuthKtorAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JWTAuthKtorAndroidTheme {
                AppNavigation()
            }
        }
    }
}