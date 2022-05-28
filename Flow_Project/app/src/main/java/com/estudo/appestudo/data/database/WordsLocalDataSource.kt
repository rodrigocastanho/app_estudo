package com.estudo.appestudo.data.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    fun fetchDefinitionText(text: String): Flow<String> {
        return flow {
            val textSubtantive = wordLocalData.presentDefinitionTextFlow().getValue(text)
            textSubtantive.forEach { sub ->
                emit(sub)
                delay(3400L)
            }
        }
    }
}