package com.mynotes.core.arch

/**
 * Created by Anurag on 25-03-2018.
 */
const val UNDEFINED_LAYOUT_ID = -1

@Retention(AnnotationRetention.RUNTIME)
annotation class Viewable(val layout: Int = UNDEFINED_LAYOUT_ID)