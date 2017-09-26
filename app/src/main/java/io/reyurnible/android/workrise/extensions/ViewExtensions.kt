package io.reyurnible.android.workrise.extensions

import android.view.View

/**
 * View Extensions
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Boolean.toVisible(invisible: Int = View.INVISIBLE): Int =
        if (this) View.VISIBLE
        else invisible

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE
