package com.estudo.appestudo.di

import com.estudo.appestudo.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataLocalModule::class, ViewModelModule::class])
interface ApplicationComponent {

     fun inject(activity: MainActivity)
}
