package io.reyurnible.android.workrise.extensions

import android.content.Context
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import java.util.*

/**
 * ValueObject Display Extension
 */
/**
 * @link YearMonthDay.kt
 */
fun YearMonthDay.toDisplay(context: Context) =
        context.getString(R.string.top_pager_title, day, context.resources.getStringArray(R.array.top_pager_weeks)[toCalendar().get(Calendar.DAY_OF_WEEK) - 1])
