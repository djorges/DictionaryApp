package com.levelabdev.dictionary.data.api

import com.levelabdev.dictionary.data.dto.WordInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/{lang}/{word}")
    suspend fun getWordInfo(
        @Path("lang") language:String,
        @Path("word") word:String
    ):List<WordInfo>
}