package com.estudo.appestudo.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.estudo.appestudo.domain.model.DefinitionWord
import com.estudo.appestudo.domain.model.Note
import com.estudo.appestudo.domain.model.Word

data class NoteEntity(
    @Embedded val wordEntity: WordEntity,
    @Relation(
        parentColumn = "wordId",
        entityColumn = "definitionId_relation"
    )
    val definitionsWordEntity: List<DefinitionWordEntity>
)

fun NoteEntity.toDomain() = Note(
    word = Word(wordEntity.text, wordEntity.trasnslate),
    definitionsWord = definitionsWordEntity.map { DefinitionWord(it.definition) }
)
