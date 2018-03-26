package com.mynotes.ui.list

import com.mynotes.core.api.ApiManager
import com.mynotes.core.arch.BasePresenter
import com.mynotes.dataclasses.Note
import com.mynotes.dataclasses.events.NewNoteEvent
import com.mynotes.dataclasses.response.GetNotesResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * Created by Anurag on 25-03-2018.
 */
class ViewNotesPresenterImpl @Inject constructor(private val apiManager: ApiManager, private val eventBus: EventBus) : BasePresenter<ViewNotesContract.View>(), ViewNotesContract.Presenter {

    override fun attachView(view: ViewNotesContract.View) {
        super.attachView(view)
        eventBus.register(this)
    }

    override fun detachView() {
        super.detachView()
        eventBus.unregister(this)
    }

    override fun getNotes() {
        apiManager.notesApi().getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetNotesResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: GetNotesResponse) {
                        getView()?.displayNotes(t.notes)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewNoteEvent(event: NewNoteEvent) {
        getView()?.newNoteAdded(event.note)
    }
}