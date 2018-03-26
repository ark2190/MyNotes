package com.mynotes.ui.create

import android.content.Context
import com.mynotes.core.api.ApiManager
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus

/**
 * Created by Anurag on 25-03-2018.
 */
@Module
class AddNoteModule(val view: AddNoteContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(context: Context, apiManager: ApiManager, eventBus: EventBus): AddNoteContract.Presenter = AddNotePresenterImpl(context, apiManager, eventBus)
}