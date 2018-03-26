package com.mynotes.ui.list

import com.mynotes.core.arch.BaseContract
import com.mynotes.dataclasses.Note

/**
 * Created by ark21 on 26-03-2018.
 */
class ViewNotesContract {
    interface View : BaseContract.View {
        fun displayNotes(notes: List<Note>)
        fun newNoteAdded(note: Note)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getNotes()
    }
}