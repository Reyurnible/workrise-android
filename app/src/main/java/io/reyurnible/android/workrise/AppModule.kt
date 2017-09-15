package io.reyurnible.android.workrise

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.presentation.PresentationModule
import javax.inject.Singleton

@Module(includes = arrayOf(DataModule::class, PresentationModule::class))
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

}
