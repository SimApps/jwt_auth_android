package com.simapp.jwtauthktorandroid.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.simapp.jwtauthktorandroid.auth.AuthApi
import com.simapp.jwtauthktorandroid.auth.AuthRepository
import com.simapp.jwtauthktorandroid.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
         //  .baseUrl("http://192.168.1.14:8080/")
           .baseUrl("https://127.0.0.1:8080/")
            // .baseUrl("http://10.0.2.2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api = api, prefs = prefs)
    }
}