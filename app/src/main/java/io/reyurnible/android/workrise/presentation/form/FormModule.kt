package io.reyurnible.android.workrise.presentation.form

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FormModule {

    @Binds
    @IntoMap
    @ActivityKey(FormActivity::class)
    abstract fun bindFormPresenter(presenter: FormPresenter): FormPresenter

    @ContributesAndroidInjector
    abstract fun contributeFormFragment(): FormFragment

    @ContributesAndroidInjector
    abstract fun contributeFormActivity(): FormActivity
}
