package io.reyurnible.android.workrise

import dagger.Module
import io.reyurnible.android.workrise.presentation.PresentationModule

@Module(includes = arrayOf(PresentationModule::class))
class AppModule
