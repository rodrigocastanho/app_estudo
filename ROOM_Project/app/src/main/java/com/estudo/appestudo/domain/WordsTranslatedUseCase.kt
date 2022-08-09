package com.estudo.appestudo.domain

import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.data.model.DefinitionWordEntity
import com.estudo.appestudo.data.model.WordEntity
import com.estudo.appestudo.data.model.toDomain
import com.estudo.appestudo.domain.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordsTranslatedUseCase @Inject constructor(
    private val wordsLocalDataRepository: WordsLocalDataRepository
) {
    suspend fun fetchDefinitionWord(word: String): Flow<Note> {
        return flow {
            wordsLocalDataRepository.fetchDefinitionWordTranslate(word)
                .map { ne -> ne.toDomain() }
                .collect { note -> emit(note) }
        }
    }

    suspend fun insertNewWordDefinitions(wordEntity: WordEntity, definitionWordEntities: List<DefinitionWordEntity>) {
        withContext(Dispatchers.IO) {
            val idWord = wordsLocalDataRepository.insertWordTranslate(wordEntity)
            definitionWordEntities.map { it.definitionIdRelation = idWord }
            wordsLocalDataRepository.insertDefinitionWord(definitionWordEntities)
        }
    }
}