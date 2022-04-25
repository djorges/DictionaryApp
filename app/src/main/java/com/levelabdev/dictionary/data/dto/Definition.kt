package com.levelabdev.dictionary.data.dto

import com.levelabdev.dictionary.domain.model.DefinitionModel

class Definition(
    val definition: String,
    val example:String,
    val synonyms:List<String>,
    val antonyms:List<String>
){
    fun toDefinitionModel(): DefinitionModel {
        return DefinitionModel(
            definition,
            example,
            synonyms,
            antonyms
        )
    }
}
