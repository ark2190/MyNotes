package com.mynotes.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Anurag on 25-03-2018.
 */
@Singleton
class ToastManager @Inject constructor(private val mContext: Context) {
    private var mToast: Toast? = null

    fun showToast(message: String) {
        cancelToastIfShowing()

        mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
        mToast!!.show()
    }

    fun showToast(strResId: Int) {
        cancelToastIfShowing()

        mToast = Toast.makeText(mContext, strResId, Toast.LENGTH_SHORT)
        mToast!!.show()
    }

    /**
     * Cancels the active toast if there's one
     */
    private fun cancelToastIfShowing() {
        //cancel the active toast if there's one
        mToast?.cancel()
    }
}