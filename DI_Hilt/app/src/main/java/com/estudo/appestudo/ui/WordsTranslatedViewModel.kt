package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.MapKey
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun translate(word: String) {
        viewModelScope.launch {
            val note = wordsTranslatedUseCase(word)
            _textTranslate.value = note.text
        }
    }
}

