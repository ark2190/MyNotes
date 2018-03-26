package com.mynotes.ui.create

import android.app.Activity
import android.content.Context
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.mynotes.R
import com.mynotes.core.App
import com.mynotes.core.arch.BaseFragment
import com.mynotes.core.arch.Viewable
import com.mynotes.dataclasses.Note
import com.mynotes.utils.ToastManager
import javax.inject.Inject

@Viewable(layout = R.layout.fragment_add_note)
class AddNoteFragment : BaseFragment<AddNoteContract.View, AddNoteContract.Presenter>(), AddNoteContract.View {

    @BindView(R.id.etNote)
    lateinit var etNote: EditText

    @Inject
    lateinit var mToastManager: ToastManager

    private var mCallbacks: AddNoteActivityCallbacks? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ((context as Activity).application as App).appComponent()
                .with(AddNoteModule(this))
                .inject(this)

        if (context is AddNoteActivityCallbacks) {
            mCallbacks = context
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun noteAdded(t: Note) {
        mCallbacks?.onNoteAdded(t)
        mToastManager.showToast("New note added")
    }

    override fun onError(errorMessage: String?) {
        mToastManager.showToast(errorMessage ?: "Unable to add note")
    }

    @OnClick(R.id.btnSave)
    fun onSaveClick() {
        mPresenter.addNote(etNote.text.toString())
    }
}
