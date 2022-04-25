package com.levelabdev.dictionary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.levelabdev.dictionary.domain.model.MeaningModel
import com.levelabdev.dictionary.domain.model.WordInfoModel

@Entity
class WordInfoEntity(
    @PrimaryKey
    val id:Int? = null,
    val word:String,
    val phonetic:String,
    val origin:String,
    val meanings: List<MeaningModel>
){
    fun toWordInfoModel():WordInfoModel{
        return WordInfoModel(
            word,
            phonetic,
            origin,
            meanings
        )
    }
}
