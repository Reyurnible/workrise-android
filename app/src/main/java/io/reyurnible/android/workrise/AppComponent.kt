package io.reyurnible.android.workrise

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class))
interface AppComponent : AndroidInjector<WorkriseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WorkriseApplication>()
}
