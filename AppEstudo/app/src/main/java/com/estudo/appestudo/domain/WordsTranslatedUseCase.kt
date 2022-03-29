package com.estudo.appestudo.domain

import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.domain.model.Note

class WordsTranslatedUseCase(
    private val wordsLocalDataRepository: WordsLocalDataRepository
) {
     suspend operator fun invoke(text: String): Note {
        val word = wordsLocalDataRepository.fetchWordTranslate()[text].toString()
        return Note(word)
    }
}