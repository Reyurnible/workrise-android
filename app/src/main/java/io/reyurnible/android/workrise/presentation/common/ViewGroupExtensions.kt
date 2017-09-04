package io.reyurnible.android.workrise.presentation.common

import android.view.View
import android.view.ViewGroup

/**
 * ViewGroup Extensions
 */
fun ViewGroup.isFirstIndexChild(view: View): Boolean =
        0 == indexOfChild(view)

fun ViewGroup.isLastIndexChild(view: View): Boolean =
        (childCount - 1) == indexOfChild(view)

fun ViewGroup.nextOrNullView(view: View): View? = kotlin.run {
    val index = indexOfChild(view)
    if ((index + 1) in (1 until childCount)) {
        getChildAt(index + 1)
    } else null
}

fun ViewGroup.prevOrNullView(view: View): View? = kotlin.run {
    val index = indexOfChild(view)
    if ((index - 1) in (0 until childCount - 1)) {
        getChildAt(index - 1)
    } else null
}

fun ViewGroup.getChildList(): List<View> =
        (0 until childCount).map { getChildAt(it) }
