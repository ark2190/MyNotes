package com.mynotes.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.mynotes.R
import com.mynotes.dataclasses.Note

/**
 * Created by Anurag on 25-03-2018.
 */

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    private val notes: MutableList<Note> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder = NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_note, null, false))

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun addNotes(notes: List<Note>) {
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    fun addNote(note: Note) {
        this.notes.add(0, note)
        notifyItemInserted(0)
    }

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.tvNote)
        lateinit var tvNote: TextView

        init {
            ButterKnife.bind(this, view)
        }

        fun bind(note: Note) {
            tvNote.text = note.body
        }
    }
}