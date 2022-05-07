package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import kotlinx.coroutines.launch

class WordsTranslatedViewModel(
    private val wordsTranslatedUseCase: WordsTranslatedUseCase
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

class WordsTranslatedViewModelFactory(
    private val wordsTranslatedUseCase: WordsTranslatedUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordsTranslatedViewModel(wordsTranslatedUseCase) as T
    }
}
