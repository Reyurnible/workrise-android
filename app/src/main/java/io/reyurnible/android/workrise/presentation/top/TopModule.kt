package io.reyurnible.android.workrise.presentation.top

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap

@Module
abstract class TopModule {

    @Binds
    @IntoMap
    @ActivityKey(TopActivity::class)
    abstract fun bindTopPresenter(presenter: TopPresenter): TopPresenter
}
