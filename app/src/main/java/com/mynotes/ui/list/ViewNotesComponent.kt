package com.mynotes.ui.list

import dagger.Subcomponent

/**
 * Created by Anurag on 25-03-2018.
 */
@Subcomponent(modules = [ViewNotesModule::class])
interface ViewNotesComponent {
    fun inject(fragment: ViewNotesFragment)
}