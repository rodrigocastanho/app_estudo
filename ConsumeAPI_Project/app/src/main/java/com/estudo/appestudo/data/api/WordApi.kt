package com.estudo.appestudo.data.api

import com.estudo.appestudo.data.api.model.NoteApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WordApi {

    @GET("words/{query}")
    suspend fun getWords(@Path("query") query: String): Response<NoteApi>

    @POST("words")
    suspend fun insertWord(@Body noteApi: NoteApi): Response<NoteApi>
}