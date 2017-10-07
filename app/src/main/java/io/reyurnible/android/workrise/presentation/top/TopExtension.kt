package io.reyurnible.android.workrise.presentation.top

import android.content.Context
import android.content.Intent

fun TopActivity.Companion.createInstance(context: Context): Intent =
        Intent(context, TopActivity::class.java)

fun TopFragment.Companion.createInstance(): TopFragment =
        TopFragment()
