package com.mynotes.ui.list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.mynotes.R
import com.mynotes.core.App
import com.mynotes.core.arch.BaseFragment
import com.mynotes.core.arch.Viewable
import com.mynotes.dataclasses.Note
import com.mynotes.ui.create.AddNoteActivity
import com.mynotes.ui.widgets.EmptyStateRecyclerView

@Viewable(layout = R.layout.fragment_view_notes)
class ViewNotesFragment : BaseFragment<ViewNotesContract.View, ViewNotesContract.Presenter>(), ViewNotesContract.View {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: EmptyStateRecyclerView

    @BindView(R.id.tvEmptyState)
    lateinit var tvEmptyState: TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        ((context as Activity).application as App).appComponent()
                .with(ViewNotesModule(this))
                .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.setEmptyView(tvEmptyState)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val divider = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(view.context, R.drawable.list_divider))
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = NotesAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.hideEmptyView()
        mPresenter.getNotes()
    }

    override fun displayNotes(notes: List<Note>) {
        (recyclerView.adapter as NotesAdapter).addNotes(notes)
    }

    override fun newNoteAdded(note: Note) {
        (recyclerView.adapter as NotesAdapter).addNote(note)
    }

    @OnClick(R.id.fabAdd)
    fun addNoteClick() {
        startActivity(Intent(this.context, AddNoteActivity::class.java))
    }
}
