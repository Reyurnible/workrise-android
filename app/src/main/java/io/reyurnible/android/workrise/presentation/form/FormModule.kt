package io.reyurnible.android.workrise.presentation.form

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.ContributesAndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import io.reyurnible.android.workrise.presentation.top.TopActivity
import io.reyurnible.android.workrise.presentation.top.TopPresenter

@Module
abstract class FormModule {

    @Binds
    @IntoMap
    @ActivityKey(FormActivity::class)
    abstract fun bindFormPresenter(presenter: FormPresenter): FormPresenter

    @ContributesAndroidInjector
    abstract fun contributeFormFragment(): FormFragment
}
