package io.reyurnible.android.workrise

import dagger.Module
import io.reyurnible.android.workrise.domain.DomainModule
import io.reyurnible.android.workrise.infrastructure.InfrastructureModule
import io.reyurnible.android.workrise.usecase.UseCaseModule

@Module(includes = arrayOf(UseCaseModule::class, DomainModule::class, InfrastructureModule::class))
class DataModule {

}
