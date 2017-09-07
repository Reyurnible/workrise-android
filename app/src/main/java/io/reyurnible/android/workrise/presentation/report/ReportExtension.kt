package io.reyurnible.android.workrise.presentation.report

import android.os.Bundle
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private object Keys {
    const val date = "date"
}

fun ReportFragment.Companion.createInstance(date: YearMonthDay): ReportFragment = ReportFragment().apply {
    arguments = Bundle().apply {
        putParcelable(Keys.date, date)
    }
}

fun ReportFragment.bindDate(): ReadOnlyProperty<ReportFragment, YearMonthDay> =
        object : ReadOnlyProperty<ReportFragment, YearMonthDay> {
            override fun getValue(thisRef: ReportFragment, property: KProperty<*>): YearMonthDay = thisRef.arguments.getParcelable(Keys.date)
        }
