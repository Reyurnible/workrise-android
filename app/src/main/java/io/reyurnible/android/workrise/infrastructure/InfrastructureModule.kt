package io.reyurnible.android.workrise.infrastructure

import android.app.Application
import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.infrastructure.realm.RealmFactory
import javax.inject.Singleton

@Module
class InfrastructureModule {

    @Singleton
    @Provides
    fun provideRealmFactory(application: Application): RealmFactory =
            RealmFactory(application)

}
