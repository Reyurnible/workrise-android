package io.reyurnible.android.workrise.presentation.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private object Keys {
    const val date = "date"
}

fun FormActivity.Companion.createIntent(context: Context, date: YearMonthDay): Intent =
        Intent(context, FormActivity::class.java).apply {
            putExtra(Keys.date, date)
        }

fun FormFragment.Companion.createInstance(date: YearMonthDay): FormFragment = FormFragment().apply {
    arguments = Bundle().apply {
        putParcelable(Keys.date, date)
    }
}

fun FormActivity.bindDate(): ReadOnlyProperty<FormActivity, YearMonthDay> =
        object : ReadOnlyProperty<FormActivity, YearMonthDay> {
            override fun getValue(thisRef: FormActivity, property: KProperty<*>): YearMonthDay = thisRef.intent.getParcelableExtra(Keys.date)
        }

fun FormFragment.bindDate(): ReadOnlyProperty<FormFragment, YearMonthDay> =
        object : ReadOnlyProperty<FormFragment, YearMonthDay> {
            override fun getValue(thisRef: FormFragment, property: KProperty<*>): YearMonthDay = thisRef.arguments.getParcelable(Keys.date)
        }
