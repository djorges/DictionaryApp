package com.levelabdev.dictionary.presentation.state

import com.levelabdev.dictionary.domain.model.WordInfoModel

data class DictionaryState(
    val wordInfoItems:List<WordInfoModel> = emptyList(),
    val isLoading: Boolean = false
)
