package com.estudo.appestudo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import kotlinx.coroutines.launch

class WordsTranslatedViewModel(
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

