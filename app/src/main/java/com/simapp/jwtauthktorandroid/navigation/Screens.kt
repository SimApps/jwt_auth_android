package com.simapp.jwtauthktorandroid.navigation

sealed class Screens(val Route: String) {
    object AuthScreens : Screens("AuthScreen")
    object SecretScreens : Screens("SecretScreen")
}