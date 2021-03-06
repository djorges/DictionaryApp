package com.levelabdev.dictionary.domain.model

sealed class Result<out T> {
    data class Loading<out T>(val data: T? = null) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val throwable: Throwable) : Result<T>()
}