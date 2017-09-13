package io.reyurnible.android.workrise.infrastructure

import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.infrastructure.realm.RealmFactory
import javax.inject.Singleton

@Module
class InfrastructureModule {

    @Singleton
    @Provides
    fun provideRealmFactory(): RealmFactory =
            RealmFactory()

}
