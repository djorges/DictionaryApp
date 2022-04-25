package com.levelabdev.dictionary.data.dto

import com.levelabdev.dictionary.domain.model.MeaningModel

class Meaning (
    val partOfSpeech:String,
    val definitions:List<Definition>
){
    fun toMeaningModel(): MeaningModel {
        return MeaningModel(
            partOfSpeech,
            definitions.map { it.toDefinitionModel() }
        )
    }
}