package com.estudo.appestudo.domain

import com.estudo.appestudo.data.api.ResultResponse
import com.estudo.appestudo.data.api.WordsApiDataRepository
import com.estudo.appestudo.data.api.model.DefinitionWordApi
import com.estudo.appestudo.data.api.model.NoteApi
import com.estudo.appestudo.data.api.model.WordApi
import com.estudo.appestudo.data.database.WordsLocalDataRepository
import com.estudo.appestudo.data.database.model.DefinitionWordEntity
import com.estudo.appestudo.data.database.model.WordEntity
import com.estudo.appestudo.data.database.model.toDomain
import com.estudo.appestudo.domain.model.DefinitionWord
import com.estudo.appestudo.domain.model.Note
import com.estudo.appestudo.domain.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordsTranslatedUseCase @Inject constructor(
    private val wordsLocalDataRepository: WordsLocalDataRepository,
    private val wordsApiDataRepository: WordsApiDataRepository
) {
    suspend fun fetchDefinitionWord(word: String): Flow<Note> {
        return flow {
            wordsLocalDataRepository.fetchDefinitionWordTranslate(word)
                .map { ne -> ne.toDomain() }
                .collect { note -> emit(note) }
        }
    }

    suspend fun insertNewWordDefinitions(
        wordEntity: WordEntity,
        definitionWordEntities: List<DefinitionWordEntity>
    ) {
        withContext(Dispatchers.IO) {
            val idWord = wordsLocalDataRepository.insertWordTranslate(wordEntity)
            definitionWordEntities.map { it.definitionIdRelation = idWord }
            wordsLocalDataRepository.insertDefinitionWord(definitionWordEntities)
        }
    }

   suspend fun fetchDefinionWordRemoteApi(word: String): ResultResponse<Note> {
       wordsApiDataRepository.getWords(word).also { response ->
           return if (response.isSuccessful) {
               ResultResponse.Sucess(response.body()?.toDomainApi())
           } else {
               // val message = response.errorBody()?.string() User later
               ResultResponse.Failure(RuntimeException("Requisition failure."))
           }
       }
   }

    suspend fun insertNewWordDefinitionsApi(
        word: Word,
        definitionWord: List<DefinitionWord>
    ) {
        val noteApi = NoteApi(
            id  = word.text,
            word = WordApi(
                text = word.text,
                trasnslate = word.trasnslate
            ),
            definitionsWord = definitionWord.map { DefinitionWordApi(it.definition) }
        )
        wordsApiDataRepository.insertWord(noteApi).also {
            println("request cod: ${it.code()}")
            println("request POST result: ${it.body()}")
        }
    }
}