package com.mynotes.core.di

import dagger.Component
import javax.inject.Singleton

/**
 * Created by Anurag on 25-03-2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

}