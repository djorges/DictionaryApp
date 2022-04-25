package com.levelabdev.dictionary.domain.model

data class DefinitionModel(
    val definition: String,
    val example:String,
    val synonyms:List<String>,
    val antonyms:List<String>
)