package com.estudo.appestudo.data.database

import com.estudo.appestudo.data.model.DefinitionWordEntity
import com.estudo.appestudo.data.model.NoteEntity
import com.estudo.appestudo.data.model.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordsLocalDataRepository @Inject constructor(
    private val wordDao: WordDao
) {

    fun fetchDefinitionWordTranslate(word: String): Flow<NoteEntity> {
        return wordDao.findByIdWordDefinitions(word)
    }

     fun insertWordTranslate(wordEntity: WordEntity): Long {
        return wordDao.insertWord(wordEntity)
    }

    fun insertDefinitionWord(definitionWordEntities: List<DefinitionWordEntity>) {
        wordDao.insertDefinition(definitionWordEntities)
    }

//    override fun fetchTranslateText(): Map<String, String> {
//        return mapOf(
//            "" to "Not the word",
//            "mundo" to "World",
//            "gerra" to "War",
//            "vida" to "Life",
//            "conhecimento" to "knowledge"
//        )
//    }

    //        return mapOf(
//            "" to listOf("No Definition word"),
//            "mundo" to listOf("world", "earth", "orb"),
//            "gerra" to listOf("war", "warfare", "battle", "struggle", "sword", "hostility"),
//            "vida" to listOf("life", "living", "lifetime", "existence", "span", "world"),
//            "conhecimento" to listOf("knowledge", "know", "knowing", "information", "learning", "acquaintance")
//        )

}