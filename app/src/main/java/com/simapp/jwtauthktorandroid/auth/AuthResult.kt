package com.simapp.jwtauthktorandroid.auth

sealed class AuthResult<T>(val data: T? = null){
    class Authorized<T>(data: T? = null): AuthResult<T>(data)
    class UnAuthorized<T>: AuthResult<T>()
    class UnKnownError<T>: AuthResult<T>()
}
