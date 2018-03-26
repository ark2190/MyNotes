package com.mynotes.ui.create

import com.mynotes.core.arch.BaseContract
import com.mynotes.dataclasses.Note
import com.mynotes.dataclasses.response.AddNoteResponse

/**
 * Created by Anurag on 25-03-2018.
 */
class AddNoteContract {
    interface View : BaseContract.View {
        fun showLoading()
        fun hideLoading()
        fun noteAdded(t: Note)
        fun onError(errorMessage: String?)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun addNote(note: String)
    }
}