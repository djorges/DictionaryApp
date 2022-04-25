package com.levelabdev.dictionary.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.levelabdev.dictionary.data.db.DictionaryDatabase
import com.levelabdev.dictionary.data.db.ListConverter
import com.levelabdev.dictionary.data.db.WordInfoDao
import com.levelabdev.dictionary.data.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DictionaryDatabase {

        return Room.databaseBuilder(
                context,
                DictionaryDatabase::class.java,
                DATABASE_NAME
            ).addTypeConverter(ListConverter(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context):WordInfoDao{
        return Retrofit.Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordInfoDao::class.java)
    }

    private const val RETROFIT_BASE_URL = "https://api.dictionaryapi.dev/"
    private const val DATABASE_NAME = "dictionary_database"
}