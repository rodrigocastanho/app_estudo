package com.estudo.appestudo.data.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordsLocalDataSource @Inject constructor(
    private val wordLocalData: WordLocalData
) {

    suspend fun fetchTranslateText(): Map<String, String> {
        return withContext(Dispatchers.Default) {
             wordLocalData.fetchTranslateText()
        }
    }

    interface WordLocalData {
        fun fetchTranslateText() = mapOf(
            "mundo" to "World",
            "gerra" to "War",
            "vida" to "life"
        )
    }
}