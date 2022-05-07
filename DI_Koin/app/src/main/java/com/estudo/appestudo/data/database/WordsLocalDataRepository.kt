package com.estudo.appestudo.data.database

class WordsLocalDataRepository(
    private val wordsLocalDataSource: WordsLocalDataSource
) {
     suspend fun fetchWordTranslate(): Map<String, String> =
         wordsLocalDataSource.fetchTranslateText()
}