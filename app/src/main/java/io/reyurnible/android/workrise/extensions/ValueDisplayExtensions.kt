package io.reyurnible.android.workrise.extensions

import android.content.Context
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * ValueObject Display Extension
 */
fun YearMonthDay.toDisplay(context: Context): String =
        "${year}/${String.format("%02d", month)}/${String.format("%02d", day)}"
