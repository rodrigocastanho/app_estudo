package com.estudo.appestudo.data.database

import javax.inject.Inject

class WordsLocalDataRepository @Inject constructor(
    private val wordsLocalDataSource: WordsLocalDataSource
) {
     suspend fun fetchWordTranslate(): Map<String, String> = wordsLocalDataSource.fetchTranslateText()
}