package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.MapKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@HiltViewModel
class WordsTranslatedViewModel @Inject constructor(
    private val wordsTranslatedUseCase: WordsTranslatedUseCase,
): ViewModel() {

    private val _textTranslate = MutableLiveData<String>()
    val textTranslate = _textTranslate as LiveData<String>

    private var _textDefinition = MutableLiveData<String>()
    val textDefinition = _textDefinition as LiveData<String>

//     Converter for flow liveData direct use, he was use method Transformations.switchMap of liveData by cause
//     of method it repository has parameter

//    private var _textDefinition = MutableLiveData<String>()
//    val textDefinition: LiveData<String> = Transformations.switchMap(_textDefinition) { word ->
//        wordsTranslatedUseCase.fetchDefinitionWord(word).asLiveData()
//    }

    fun translate(word: String) {
        viewModelScope.launch {
            val note = wordsTranslatedUseCase(word)
            _textTranslate.value = note.text
//            _textDefinition.value = word

//          Using flow with collect operator and map for update word for uppercase
            wordsTranslatedUseCase.fetchDefinitionWord(word)
                .map { w ->
                    w.uppercase()
                }.collect { w ->
                    _textDefinition.value = w
                }
        }
    }

}

