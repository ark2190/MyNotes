package com.mynotes.core.arch

/**
 * Created by Anurag on 25-03-2018.
 */
open class BaseContract {

    interface View

    interface Presenter<V : BaseContract.View?> {
        fun attachView(view: V)

        fun detachView()

        fun getView(): V?
    }
}