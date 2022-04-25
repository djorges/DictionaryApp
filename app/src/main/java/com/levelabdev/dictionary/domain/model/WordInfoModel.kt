package com.levelabdev.dictionary.domain.model

data class WordInfoModel(
    val word:String,
    val phonetic:String,
    val origin:String,
    val meanings:List<MeaningModel>,
)
