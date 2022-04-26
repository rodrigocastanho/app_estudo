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
}