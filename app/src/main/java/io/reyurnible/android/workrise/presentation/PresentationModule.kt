package io.reyurnible.android.workrise.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.reyurnible.android.workrise.presentation.form.FormActivity
import io.reyurnible.android.workrise.presentation.form.FormModule
import io.reyurnible.android.workrise.presentation.top.TopActivity
import io.reyurnible.android.workrise.presentation.top.TopModule

@Module
abstract class PresentationModule {

    @ContributesAndroidInjector(modules = arrayOf(TopModule::class))
    abstract fun contributeTopActivity(): TopActivity

    @ContributesAndroidInjector(modules = arrayOf(FormModule::class))
    abstract fun contributeFormActivity(): FormActivity
}