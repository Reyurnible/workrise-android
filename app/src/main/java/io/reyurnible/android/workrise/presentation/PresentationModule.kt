package io.reyurnible.android.workrise.presentation

import dagger.Module
import io.reyurnible.android.workrise.presentation.form.FormModule
import io.reyurnible.android.workrise.presentation.report.ReportModule
import io.reyurnible.android.workrise.presentation.reportsetting.ReportSettingModule
import io.reyurnible.android.workrise.presentation.top.TopModule

@Module(includes = arrayOf(
        FormModule::class,
        ReportModule::class,
        ReportSettingModule::class,
        TopModule::class)
)
class PresentationModule
