package com.levelabdev.dictionary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.levelabdev.dictionary.data.entity.WordInfoEntity

@Database(entities = [WordInfoEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun getDao():WordInfoDao
}