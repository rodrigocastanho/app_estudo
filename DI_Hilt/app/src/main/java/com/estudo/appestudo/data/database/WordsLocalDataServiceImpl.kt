package com.estudo.appestudo.data.database

import javax.inject.Inject

class WordsLocalDataServiceImpl @Inject constructor(): WordLocalData {

    override fun fetchTranslateText(): Map<String, String> {
        return mapOf(
            "mundo" to "World",
            "gerra" to "War",
            "vida" to "Life",
            "knowlegement" to "Conhecimento"
        )
    }
}

interface WordLocalData {
    fun fetchTranslateText(): Map<String, String>
}
