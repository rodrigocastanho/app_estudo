package com.estudo.appestudo.di

import com.estudo.appestudo.data.database.WordsLocalDataSource.WordLocalData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataLocalModule {

    @Singleton
    @Provides
    fun provideWordsLocalData(): WordLocalData {
        return object: WordLocalData {}
    }
}