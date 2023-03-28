package com.estudo.appestudo.data.api

import com.estudo.appestudo.data.api.model.NoteApi
import retrofit2.Response
import javax.inject.Inject

class WordsApiDataRepository @Inject constructor(
    private val wordApi: WordApi
): WordApi {

    override suspend fun getWords(query: String): Response<NoteApi> {
        return wordApi.getWords(query)
    }

    override suspend fun insertWord(noteApi: NoteApi): Response<NoteApi> {
        return wordApi.insertWord(noteApi)
    }
}