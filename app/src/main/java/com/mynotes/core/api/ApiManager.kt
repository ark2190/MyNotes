package com.mynotes.core.api

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Anurag on 25-03-2018.
 */
@Singleton
class ApiManager @Inject constructor(private val retrofit: Retrofit) {
    private val mNotesApi: NotesApi = retrofit.create(NotesApi::class.java)

    fun notesApi(): NotesApi = mNotesApi
}