package com.estudo.appestudo.data.api.model

import com.estudo.appestudo.domain.model.DefinitionWord
import com.estudo.appestudo.domain.model.Note
import com.estudo.appestudo.domain.model.Word

data class NoteApi(
    var id: String = "",
    var word: WordApi,
    var definitionsWord: List<DefinitionWordApi>
) {
    fun toDomainApi() = Note(
        word = Word(
            this.word.text,
            this.word.trasnslate
        ),
        definitionsWord = this.definitionsWord.map { DefinitionWord(it.definition) }
    )
}