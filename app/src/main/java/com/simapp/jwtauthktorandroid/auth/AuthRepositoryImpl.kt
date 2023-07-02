package com.simapp.jwtauthktorandroid.auth

import android.content.SharedPreferences
import retrofit2.HttpException


class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
): AuthRepository {
    override suspend fun signUp(username: String, password: String): AuthResult<Unit> {
       return try {
       api.signUp(
           request = AuthRequest(
               username = username,
               password = password
           )
       )
           signIn(username = username, password = password)
       } catch (e: HttpException){
           if(e.code() == 401)
               AuthResult.UnAuthorized()
           else AuthResult.UnKnownError()
       } catch (e: Exception){
           AuthResult.UnKnownError()
       }

    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        return try {
         val response =   api.signIn(
                request = AuthRequest(
                    username = username,
                    password = password
                )
            )

            prefs.edit().putString("jwt", response.token)
                .apply()

            AuthResult.Authorized()

        } catch (e: HttpException){
            if(e.code() == 401)
                AuthResult.UnAuthorized()
            else AuthResult.UnKnownError()
        } catch (e: Exception){
            AuthResult.UnKnownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.UnAuthorized()

            api.authenticate(
                "Bearer $token"
            )
            AuthResult.Authorized()
        } catch (e: HttpException){
            if(e.code() == 401)
                AuthResult.UnAuthorized()
            else AuthResult.UnKnownError()
        } catch (e: Exception){
            AuthResult.UnKnownError()
        }
    }
}