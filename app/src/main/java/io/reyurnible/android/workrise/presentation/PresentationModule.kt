package io.reyurnible.android.workrise.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.reyurnible.android.workrise.presentation.form.FormActivity
import io.reyurnible.android.workrise.presentation.form.FormModule
import io.reyurnible.android.workrise.presentation.report.ReportFragment
import io.reyurnible.android.workrise.presentation.report.ReportModule
import io.reyurnible.android.workrise.presentation.top.TopActivity
import io.reyurnible.android.workrise.presentation.top.TopModule

@Module(includes = arrayOf(
        TopModule::class,
        FormModule::class,
        ReportModule::class)
)
abstract class PresentationModule {

}
