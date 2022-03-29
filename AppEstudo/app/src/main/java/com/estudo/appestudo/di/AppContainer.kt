package com.estudo.appestudo.di

import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.data.database.WordsLocalDataSource
import com.estudo.appestudo.domain.WordsTranslatedUseCase

class AppContainer {

     private val wordLocalData = object: WordsLocalDataSource.WordLocalData {}
     private val wordsLocalDataSource = WordsLocalDataSource(wordLocalData)
     private val wordsLocalDataRepository = WordsLocalDataRepository(wordsLocalDataSource)

     val wordsTranslatedUseCase = WordsTranslatedUseCase(wordsLocalDataRepository)

}