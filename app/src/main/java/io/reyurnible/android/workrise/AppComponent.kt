package io.reyurnible.android.workrise

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: WorkriseApplication)
}
