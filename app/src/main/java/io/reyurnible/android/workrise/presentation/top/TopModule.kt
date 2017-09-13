package io.reyurnible.android.workrise.presentation.top

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TopModule {

    @Binds
    @IntoMap
    @ActivityKey(TopActivity::class)
    internal abstract fun bindInjectorFactory(builder: TopComponent.Builder): AndroidInjector.Factory<out Activity>
}
