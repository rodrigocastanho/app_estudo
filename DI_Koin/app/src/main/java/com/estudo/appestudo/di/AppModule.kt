package com.estudo.appestudo.di

import com.estudo.appestudo.data.database.WordLocalData
import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.data.database.WordsLocalDataServiceImpl
import com.estudo.appestudo.data.database.WordsLocalDataSource
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import com.estudo.appestudo.ui.WordsTranslatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<WordLocalData> { WordsLocalDataServiceImpl() }
    factory { WordsLocalDataSource(get()) }
    factory { WordsLocalDataRepository(get()) }
    factory { WordsTranslatedUseCase(get()) }
    viewModel { WordsTranslatedViewModel(get()) }
}

