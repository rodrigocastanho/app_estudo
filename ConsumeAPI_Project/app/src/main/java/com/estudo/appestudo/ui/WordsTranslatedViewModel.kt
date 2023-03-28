package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.data.api.ResultResponse
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

    private val _statusExibirMessageError = MutableStateFlow(false)
    var statusExibirMessageError = _statusExibirMessageError.asStateFlow()

    private val _statusMessageError = MutableStateFlow(String())
    var statusMessageError = _statusMessageError


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

    fun fetchWordAndDefinitionsApi(word: String) {
        viewModelScope.launch {
            val resultResponse = wordsTranslatedUseCase.fetchDefinionWordRemoteApi(word)
            resultResponse.also { result ->
                when(result) {
                    is ResultResponse.Sucess -> {
                        result.date?.let { note ->
                            _textTranslate.value = note.word.trasnslate
                            _textDefinition.value = note.definitionsWord.map { it.definition }
                        }
                    }
                    is ResultResponse.Failure -> {
                        _statusExibirMessageError.value = true
                        _statusMessageError.value = result.error.message.toString()
                    }
                }
            }
        }
    }
}

