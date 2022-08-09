package com.estudo.appestudo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estudo.appestudo.data.model.DefinitionWordEntity
import com.estudo.appestudo.data.model.WordEntity
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertNewWordViewModel @Inject constructor(
    private val wordsTranslatedUseCase: WordsTranslatedUseCase
): ViewModel() {

    private val definitions = mutableSetOf<DefinitionWordEntity>()
    private val _mensagem = MutableLiveData<Boolean>()
    val mensagem = _mensagem as LiveData<Boolean>

    fun insertDefinitionWord(textDefinition: String) {
        val definitionWord = DefinitionWordEntity(definition = textDefinition)
        definitions.add(definitionWord)
    }

    fun salvarNewWord(textWord: String, textTranslation: String) {
        viewModelScope.launch {
            try {
                val word = WordEntity(
                    0L,
                    textWord,
                    textTranslation,
                    "version 2"
                )
                wordsTranslatedUseCase.insertNewWordDefinitions(word, definitions.toList())
                definitions.clear()
                _mensagem.value = true
            } catch (e: Exception) {
                _mensagem.value
            }
        }
    }

}