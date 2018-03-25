package com.mynotes.core.arch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import javax.inject.Inject

/**
 * Created by Anurag on 25-03-2018.
 */
open class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(), BaseContract.View {

    @Inject
    protected lateinit var mPresenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            mPresenter.attachView(this as V)
        } catch (e: UninitializedPropertyAccessException) {
        }

        val layoutResId = javaClass.getAnnotation(Viewable::class.java).layout
        return if (layoutResId != -1) {
            val rootView = inflater.inflate(layoutResId, container, false)
            ButterKnife.bind(this, rootView)
            rootView
        } else null
    }

    fun onBackPressed(): Boolean = false

    override fun onDestroyView() {
        try {
            mPresenter.detachView()
        } catch (e: UninitializedPropertyAccessException) {
        }
        super.onDestroyView()
    }
}