package com.levelabdev.dictionary.data.dto

import com.levelabdev.dictionary.data.entity.WordInfoEntity

class WordInfo(
    val word:String,
    val phonetic:String,
    val phonetics: List<Phonetic>,
    val origin:String,
    val meanings:List<Meaning>,
){
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            word,
            phonetic,
            origin,
            meanings.map { it.toMeaningModel() }
        )
    }
}
