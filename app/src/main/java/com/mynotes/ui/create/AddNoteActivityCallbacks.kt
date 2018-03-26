package com.mynotes.ui.create

import com.mynotes.dataclasses.Note

/**
 * Created by Anurag on 25-03-2018.
 */
interface AddNoteActivityCallbacks {
    fun onNoteAdded(note: Note)
}