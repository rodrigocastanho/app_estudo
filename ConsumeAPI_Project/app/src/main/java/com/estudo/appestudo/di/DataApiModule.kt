package com.estudo.appestudo.di

import com.estudo.appestudo.data.api.AppBaseApi
import com.estudo.appestudo.data.api.WordApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataApiModule {

    @Singleton
    @Provides
    fun prodvideRetrofi(): WordApi = AppBaseApi.retrofit()
}