package io.reyurnible.android.workrise.presentation.top

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface TopComponent : AndroidInjector<TopActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TopActivity>()

}
