package com.estudo.appestudo.ui

import androidx.lifecycle.*
import com.estudo.appestudo.domain.WordsTranslatedUseCase
import dagger.MapKey
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

class WordsTranslatedViewModel @Inject constructor(
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

// That class factory of ViewModel when have parameter in method is generics
@Singleton
@Suppress("UNCHECKED_CAST")
class ViewModelGenericFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

// Annotation make in kotlin for identify class of ViewModel current
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
