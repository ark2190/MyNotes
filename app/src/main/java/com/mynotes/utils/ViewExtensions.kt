package com.mynotes.utils

import android.view.View

/**
 * Created by Anurag on 25-03-2018.
 */
inline fun View.visible() {
    this.visibility = View.VISIBLE
}

inline fun View.invisible() {
    this.visibility = View.INVISIBLE
}

inline fun View.gone() {
    this.visibility = View.GONE
}

