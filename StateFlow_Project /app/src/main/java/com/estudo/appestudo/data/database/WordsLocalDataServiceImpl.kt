package com.estudo.appestudo.data.database

import javax.inject.Inject

class WordsLocalDataServiceImpl @Inject constructor(): WordLocalData {

    override fun fetchTranslateText(): Map<String, String> {
        return mapOf(
            "" to "Not the word",
            "mundo" to "World",
            "gerra" to "War",
            "vida" to "Life",
            "conhecimento" to "knowledge"
        )
    }

    override fun presentDefinitionTextFlow(): Map<String, List<String>> {
        return mapOf(
            "" to listOf("No Definition word"),
            "mundo" to listOf("world", "earth", "orb"),
            "gerra" to listOf("war", "warfare", "battle", "struggle", "sword", "hostility"),
            "vida" to listOf("life", "living", "lifetime", "existence", "span", "world"),
            "conhecimento" to listOf("knowledge", "know", "knowing", "information", "learning", "acquaintance")
        )
    }
}

interface WordLocalData {
    fun fetchTranslateText(): Map<String, String>
    fun presentDefinitionTextFlow(): Map<String, List<String>>
}
