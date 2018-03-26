package com.mynotes.core.di

import com.mynotes.ui.create.AddNoteComponent
import com.mynotes.ui.create.AddNoteModule
import com.mynotes.ui.list.ViewNotesComponent
import com.mynotes.ui.list.ViewNotesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Anurag on 25-03-2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun with(module: AddNoteModule): AddNoteComponent
    fun with(module: ViewNotesModule): ViewNotesComponent
}