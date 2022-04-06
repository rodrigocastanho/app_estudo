package com.estudo.appestudo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estudo.appestudo.ui.ViewModelGenericFactory
import com.estudo.appestudo.ui.ViewModelKey
import com.estudo.appestudo.ui.WordsTranslatedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(
        viewModelGenericFactory: ViewModelGenericFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordsTranslatedViewModel::class)
    abstract fun wordsTranslatedViewModel(
        wordsTranslatedViewModel: WordsTranslatedViewModel
    ): ViewModel
}