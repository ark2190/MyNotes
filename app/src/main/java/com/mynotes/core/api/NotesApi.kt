package com.mynotes.core.api

/**
 * Created by Anurag on 25-03-2018.
 */
interface NotesApi {
    companion object {
        const val ADD_NOTE = "add_note";
        const val EDIT_NOTE = "edit_note";
        const val DELETE_NOTE = "delete_note";
        const val VIEW_NOTES = "view_notes";
    }
}