package io.reyurnible.android.workrise.domain

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.domain.repository.ReportRepository
import io.reyurnible.android.workrise.domain.repository.ReportRepositoryImpl
import io.reyurnible.android.workrise.domain.repository.ReportSettingRepository
import io.reyurnible.android.workrise.domain.repository.ReportSettingRepositoryImpl
import io.reyurnible.android.workrise.domain.service.CheckItemCheckService
import io.reyurnible.android.workrise.domain.service.CheckItemCheckServiceImpl
import io.reyurnible.android.workrise.infrastructure.InfrastructureModule
import io.reyurnible.android.workrise.infrastructure.pref.WorkrisePreferences
import io.reyurnible.android.workrise.infrastructure.realm.RealmFactory
import javax.inject.Singleton

@Module(includes = arrayOf(InfrastructureModule::class))
class DomainModule {

    @Singleton
    @Provides
    fun provideReportRepository(realmFactory: RealmFactory): ReportRepository =
            ReportRepositoryImpl(realmFactory)

    @Singleton
    @Provides
    fun provideReportSettingRepository(context: Context, workrisePreferences: WorkrisePreferences): ReportSettingRepository =
            ReportSettingRepositoryImpl(context, workrisePreferences)

    @Singleton
    @Provides
    fun provideCheckItemCheckService(realmFactory: RealmFactory): CheckItemCheckService =
            CheckItemCheckServiceImpl(realmFactory)

}
