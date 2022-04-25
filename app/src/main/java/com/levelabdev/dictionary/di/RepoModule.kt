package com.levelabdev.dictionary.di

import com.levelabdev.dictionary.data.api.DictionaryApi
import com.levelabdev.dictionary.data.repository.DictionaryRepositoryImpl
import com.levelabdev.dictionary.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindDictionaryRepo(
        dictionaryRepositoryImpl: DictionaryRepositoryImpl
    ): DictionaryRepository
}