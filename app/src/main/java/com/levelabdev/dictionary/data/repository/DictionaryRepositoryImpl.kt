package com.levelabdev.dictionary.data.repository

import com.levelabdev.dictionary.data.api.DictionaryApi
import com.levelabdev.dictionary.data.db.DictionaryDatabase
import com.levelabdev.dictionary.domain.repository.DictionaryRepository
import com.levelabdev.dictionary.domain.model.Result
import com.levelabdev.dictionary.domain.model.WordInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val api: DictionaryApi,
    private val database: DictionaryDatabase
):DictionaryRepository {
    override fun getWordInfo(language:String,word: String): Flow<Result<List<WordInfoModel>>> = flow{
        emit(Result.Loading())

        //Get wordInfo local
        val localWordInfos = database.getDao().getAll().map{ it.toWordInfoModel()}
        emit(Result.Loading(data = localWordInfos))

        try {
            //Get wordInfo remote
            val remoteWordInfos = api.getWordInfo(language,word)
            //Update wordInfo local
            database.getDao().deleteByWord(remoteWordInfos.map { it.word })
            database.getDao().insert(remoteWordInfos.map { it.toWordInfoEntity() })
        }catch(e:HttpException){
            emit(Result.Failure(e))
        }catch(e: IOException){
            emit(Result.Failure(e))
        }
        val updatedWordInfos = database.getDao().getByWord(word).map { it.toWordInfoModel() }
        emit(Result.Success(updatedWordInfos))
    }
}