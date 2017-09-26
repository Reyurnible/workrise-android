package io.reyurnible.android.workrise.extensions

import android.content.Context
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * ValueObject Display Extension
 */
/**
 * @link YearMonthDay.kt
 */
fun YearMonthDay.toDisplay(context: Context) =
        // FIXME replace to resources
        "${month}月${day}日"
