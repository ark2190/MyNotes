package com.mynotes.dataclasses.response

import com.mynotes.dataclasses.Note

/**
 * Created by Anurag on 25-03-2018.
 */
data class GetNotesResponse(val notes: List<Note>)