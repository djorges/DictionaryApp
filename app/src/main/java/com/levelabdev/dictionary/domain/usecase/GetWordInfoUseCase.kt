package com.levelabdev.dictionary.domain.usecase

import com.levelabdev.dictionary.domain.model.WordInfoModel
import com.levelabdev.dictionary.domain.repository.DictionaryRepository
import com.levelabdev.dictionary.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val repository: DictionaryRepository
) {
    operator fun invoke(word:String): Flow<Result<List<WordInfoModel>>> {
        if(word.isBlank()){
            return flow{ }
        }
        return repository.getWordInfo( "en",word)
    }
}