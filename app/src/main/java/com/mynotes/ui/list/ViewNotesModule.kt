package com.mynotes.ui.list

import com.mynotes.core.api.ApiManager
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus

/**
 * Created by Anurag on 25-03-2018.
 */

@Module
class ViewNotesModule(private val view: ViewNotesContract.View) {
    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(apiManager: ApiManager, eventBus: EventBus): ViewNotesContract.Presenter = ViewNotesPresenterImpl(apiManager, eventBus)
}