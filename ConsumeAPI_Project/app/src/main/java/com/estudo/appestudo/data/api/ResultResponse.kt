package com.estudo.appestudo.data.api

sealed class ResultResponse<out C: Any?> {
    data class Sucess<out T>(val date: T?): ResultResponse<T>()
    data class Failure(val error: Throwable): ResultResponse<Nothing>()
}
