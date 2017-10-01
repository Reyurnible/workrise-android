package io.reyurnible.android.workrise.presentation.reportsetting

import android.content.Context
import android.content.Intent

fun ReportSettingActivity.Companion.createInstance(context: Context): Intent =
        Intent(context, ReportSettingActivity::class.java)

fun ReportSettingFragment.Companion.createInstance(): ReportSettingFragment =
        ReportSettingFragment()
