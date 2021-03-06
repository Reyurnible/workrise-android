package io.reyurnible.android.workrise.usecase

import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.domain.DomainModule
import io.reyurnible.android.workrise.domain.repository.ReportRepository
import io.reyurnible.android.workrise.domain.repository.ReportSettingRepository
import io.reyurnible.android.workrise.domain.service.CheckItemCheckService
import io.reyurnible.android.workrise.usecase.checkitem.CheckCheckItemUseCase
import io.reyurnible.android.workrise.usecase.checkitem.CheckCheckItemUseCaseImpl
import io.reyurnible.android.workrise.usecase.report.*
import io.reyurnible.android.workrise.usecase.reportsetting.EditReportSettingUseCase
import io.reyurnible.android.workrise.usecase.reportsetting.EditReportSettingUseCaseImpl
import io.reyurnible.android.workrise.usecase.reportsetting.GetReportSettingUseCase
import io.reyurnible.android.workrise.usecase.reportsetting.GetReportSettingUseCaseImpl
import javax.inject.Singleton

@Module(includes = arrayOf(DomainModule::class))
class UseCaseModule {

    @Singleton
    @Provides
    fun provideCreateReportUseCase(reportRepository: ReportRepository): CreateReportUseCase =
            CreateReportUseCaseImpl(reportRepository)

    @Singleton
    @Provides
    fun provideGetDailyReportListUseCase(reportRepository: ReportRepository): GetDailyReportListUseCase =
            GetDailyReportListUseCaseImpl(reportRepository)

    @Singleton
    @Provides
    fun provideGetReportUseCase(reportRepository: ReportRepository): GetReportUseCase =
            GetReportUseCaseImpl(reportRepository)

    @Singleton
    @Provides
    fun provideEditReportSettingUseCase(reportSettingRepository: ReportSettingRepository): EditReportSettingUseCase =
            EditReportSettingUseCaseImpl(reportSettingRepository)

    @Singleton
    @Provides
    fun provideGetReportSettingUseCase(reportSettingRepository: ReportSettingRepository): GetReportSettingUseCase =
            GetReportSettingUseCaseImpl(reportSettingRepository)

    @Singleton
    @Provides
    fun provideCheckCheckItemUseCase(checkItemCheckService: CheckItemCheckService): CheckCheckItemUseCase =
            CheckCheckItemUseCaseImpl(checkItemCheckService)
}
