package com.estudo.appestudo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DefinitionWordEntity(
    @PrimaryKey(autoGenerate = true) val definitionId: Long = 0L,
    @ColumnInfo(name = "definitionId_relation") var definitionIdRelation: Long = 0L,
    @ColumnInfo(name = "t_definition") var definition: String
)