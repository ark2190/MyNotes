package com.mynotes.core.arch

import java.lang.ref.WeakReference

/**
 * Created by Anurag on 25-03-2018.
 */

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    private var mView: WeakReference<V>? = null

    override fun getView(): V? = mView?.get()

    override fun attachView(view: V) {
        mView = WeakReference(view)
    }

    override fun detachView() {
        mView = null
    }
}