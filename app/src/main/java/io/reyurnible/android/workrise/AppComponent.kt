package io.reyurnible.android.workrise

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.reyurnible.android.workrise.presentation.PresentationModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        PresentationModule::class
))
interface AppComponent : AndroidInjector<WorkriseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WorkriseApplication): Builder

        fun build(): AppComponent
    }

    override fun inject(app: WorkriseApplication)
}
