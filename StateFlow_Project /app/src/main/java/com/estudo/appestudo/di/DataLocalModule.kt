package com.estudo.appestudo.di

import com.estudo.appestudo.data.database.WordLocalData
import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.data.database.WordsLocalDataServiceImpl
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataLocalModule {

    @Singleton
    @Binds
    abstract fun bindWordsLocalDataSource(
        wordsLocalDataServiceImpl: WordsLocalDataServiceImpl
    ): WordLocalData
}
