package com.estudo.appestudo.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordsLocalDataRepository @Inject constructor(
    private val wordsLocalDataSource: WordsLocalDataSource
) {
     suspend fun fetchWordTranslate(): Map<String, String> = wordsLocalDataSource.fetchTranslateText()

    fun fetchDefinitionWordTranslate(word: String): Flow<String> {
        return wordsLocalDataSource.fetchDefinitionText(word)
    }

}