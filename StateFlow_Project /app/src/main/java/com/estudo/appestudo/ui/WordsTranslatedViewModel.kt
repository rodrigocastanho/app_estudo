package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.MapKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@HiltViewModel
class WordsTranslatedViewModel @Inject constructor(
    private val wordsTranslatedUseCase: WordsTranslatedUseCase,
): ViewModel() {

    private val _textTranslate = MutableStateFlow(String())
    val textTranslate: StateFlow<String> = _textTranslate

//    private var _textDefinition = MutableStateFlow(String())
//    val textDefinition: StateFlow<String> = _textDefinition

    private var _textDefinition = MutableSharedFlow<String>()
    val textDefinition: SharedFlow<String> = _textDefinition.asSharedFlow()

    fun translate(word: String) {
        viewModelScope.launch {
            val note = wordsTranslatedUseCase(word)
            _textTranslate.value = note.text

//          Was used method .stateIn for converter flow in stateFlow
//            wordsTranslatedUseCase.fetchDefinitionWord(word)
//                .stateIn(scope = viewModelScope)
//                .map { w ->
//                    w.uppercase()
//                }.collect { w ->
//                    _textDefinition.value = w
//            }


            wordsTranslatedUseCase.fetchDefinitionWord(word)
                .stateIn(scope = viewModelScope)
                .shareIn(
                    scope = viewModelScope,
                    started = SharingStarted.Lazily,
                    replay = 1
                ).collect {
                    _textDefinition.emit(it)
                }

        }
    }
}

