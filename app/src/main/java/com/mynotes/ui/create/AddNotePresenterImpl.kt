package com.mynotes.ui.create

import android.content.Context
import com.mynotes.R
import com.mynotes.core.api.ApiManager
import com.mynotes.core.arch.BasePresenter
import com.mynotes.dataclasses.events.NewNoteEvent
import com.mynotes.dataclasses.request.AddNoteRequest
import com.mynotes.dataclasses.response.AddNoteResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by Anurag on 25-03-2018.
 */
class AddNotePresenterImpl @Inject constructor(private val context: Context, private val apiManager: ApiManager, private val eventBus: EventBus) : BasePresenter<AddNoteContract.View>(), AddNoteContract.Presenter {
    @Suppress("PrivatePropertyName")
    private val MAX_NOTE_LENGTH = context.resources.getInteger(R.integer.max_note_length)

    override fun addNote(note: String) {
        val view = getView()
        val actualNote = note.trim()
        if (actualNote.isEmpty()) {
            view?.onError("Note cannot be blank")
            return
        }

        if (actualNote.length > MAX_NOTE_LENGTH) {
            view?.onError("Note cannot exceed maximum of $MAX_NOTE_LENGTH characters.")
            return
        }

        view?.showLoading()
        apiManager.notesApi().addNote(AddNoteRequest(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<AddNoteResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: AddNoteResponse) {
                        eventBus.post(NewNoteEvent(t.note))
                        getView()?.noteAdded(t.note)
                    }

                    override fun onError(e: Throwable) {
                        getView()?.onError(e.message)
                    }

                    override fun onComplete() {
                        getView()?.hideLoading()
                    }
                })
    }
}