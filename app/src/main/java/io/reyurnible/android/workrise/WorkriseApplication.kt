package io.reyurnible.android.workrise

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WorkriseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().create(this)
}
