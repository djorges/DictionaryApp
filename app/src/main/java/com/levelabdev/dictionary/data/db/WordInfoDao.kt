package com.levelabdev.dictionary.data.db

import androidx.room.*
import com.levelabdev.dictionary.data.entity.WordInfoEntity

@Dao
interface WordInfoDao{
    @Query("SELECT * FROM wordinfoentity")
    suspend fun getAll(): List<WordInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities:List<WordInfoEntity>)

    @Update
    suspend fun update(entity: WordInfoEntity)

    @Delete
    suspend fun delete(entity: WordInfoEntity)

    @Query("DELETE FROM wordinfoentity WHERE word IN (:words)")
    suspend fun deleteByWord(words:List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%'||:word||'%'")
    suspend fun getByWord(word: String): List<WordInfoEntity>
}