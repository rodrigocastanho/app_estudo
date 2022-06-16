package com.estudo.appestudo.domain

import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordsTranslatedUseCase @Inject constructor(
    private val wordsLocalDataRepository: WordsLocalDataRepository
) {
     suspend operator fun invoke(text: String): Note {
        val word = wordsLocalDataRepository.fetchWordTranslate()[text].toString()
        return Note(word)
    }

    fun fetchDefinitionWord(word: String): Flow<String> {
        return wordsLocalDataRepository.fetchDefinitionWordTranslate(word)
    }
}