package com.estudo.appestudo.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val wordId: Long = 0L,
    @ColumnInfo(name = "text_word") val text: String,
    @ColumnInfo(name = "text_word_translate") val trasnslate: String,
    @ColumnInfo(name = "test_migration") val testMigration: String,
)
