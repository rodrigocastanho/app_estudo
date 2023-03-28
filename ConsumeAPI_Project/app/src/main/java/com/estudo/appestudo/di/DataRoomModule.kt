package com.estudo.appestudo.di

import android.content.Context
import com.estudo.appestudo.data.database.AppDataBase
import com.estudo.appestudo.data.database.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRoomModule {

    private const val DATABASE_NAME = "word_base"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = AppDataBase.getDataBase(context, DATABASE_NAME)

    @Singleton
    @Provides
    fun provideWordDao(db: AppDataBase): WordDao = db.wordDao()
}