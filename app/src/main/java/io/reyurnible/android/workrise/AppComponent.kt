package io.reyurnible.android.workrise

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.reyurnible.android.workrise.domain.DomainModule
import io.reyurnible.android.workrise.infrastructure.InfrastructureModule
import io.reyurnible.android.workrise.presentation.top.TopModule
import io.reyurnible.android.workrise.usecase.UseCaseModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        TopModule::class,
        UseCaseModule::class,
        DomainModule::class,
        InfrastructureModule::class
))
interface AppComponent : AndroidInjector<WorkriseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WorkriseApplication>()
}
