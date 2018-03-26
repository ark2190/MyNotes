package com.mynotes.core.api

import com.mynotes.dataclasses.Note
import com.mynotes.dataclasses.request.AddNoteRequest
import com.mynotes.dataclasses.response.AddNoteResponse
import com.mynotes.dataclasses.response.GetNotesResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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

    @POST(ADD_NOTE)
    fun addNote(@Body request: AddNoteRequest): Observable<AddNoteResponse>

    @GET(VIEW_NOTES)
    fun getNotes(): Observable<GetNotesResponse>
}