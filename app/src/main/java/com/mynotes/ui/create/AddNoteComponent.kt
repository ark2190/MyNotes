package com.mynotes.ui.create

import dagger.Subcomponent

/**
 * Created by Anurag on 25-03-2018.
 */
@Subcomponent(modules = [AddNoteModule::class])
interface AddNoteComponent {
    fun inject(fragment: AddNoteFragment)
}