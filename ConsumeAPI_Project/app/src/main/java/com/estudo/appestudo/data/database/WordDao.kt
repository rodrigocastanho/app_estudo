package com.estudo.appestudo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.estudo.appestudo.data.database.model.DefinitionWordEntity
import kotlinx.coroutines.flow.Flow
import com.estudo.appestudo.data.database.model.NoteEntity
import com.estudo.appestudo.data.database.model.WordEntity

@Dao
interface WordDao {

    @Insert
    fun insertWord(wordEntity: WordEntity): Long

    @Insert
    fun insertDefinition(definitionWordEntities: List<DefinitionWordEntity>)

    @Transaction
    @Query("SELECT * FROM WordEntity WHERE WordEntity.text_word LIKE :text")
    fun findByIdWordDefinitions(text: String): Flow<NoteEntity>

}