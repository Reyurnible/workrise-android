package io.reyurnible.android.workrise.infrastructure

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.infrastructure.pref.WorkrisePreferences
import io.reyurnible.android.workrise.infrastructure.realm.RealmFactory
import javax.inject.Singleton

@Module
class InfrastructureModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
            Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideWorkrisePreferences(context: Context, moshi: Moshi): WorkrisePreferences =
            WorkrisePreferences(context, moshi)

    @Singleton
    @Provides
    fun provideRealmFactory(context: Context): RealmFactory =
            RealmFactory(context)

}
