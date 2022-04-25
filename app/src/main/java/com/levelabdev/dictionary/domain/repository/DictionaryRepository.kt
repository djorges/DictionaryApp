package com.levelabdev.dictionary.domain.repository

import com.levelabdev.dictionary.domain.model.WordInfoModel
import com.levelabdev.dictionary.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    fun getWordInfo(language:String, word:String): Flow<Result<List<WordInfoModel>>>
}