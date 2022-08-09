package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsTranslatedViewModel @Inject constructor(
    private val wordsTranslatedUseCase: WordsTranslatedUseCase
): ViewModel() {

    private val _textTranslate = MutableStateFlow(String())
    val textTranslate = _textTranslate.asStateFlow()

    private var _textDefinition = MutableStateFlow(listOf("Show definitions"))
    val textDefinition = _textDefinition.asStateFlow()

    fun fetchWordWithDefinitions(word: String) {
        viewModelScope.launch {
            wordsTranslatedUseCase.fetchDefinitionWord(word)
                .stateIn(viewModelScope)
                .collect { note ->
                    _textTranslate.value = note.word.trasnslate
                    _textDefinition.value = note.definitionsWord.map { it.definition }
            }
        }
    }
}

