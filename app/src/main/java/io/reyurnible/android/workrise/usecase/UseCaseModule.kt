package io.reyurnible.android.workrise.usecase

import dagger.Module
import dagger.Provides
import io.reyurnible.android.workrise.domain.DomainModule
import io.reyurnible.android.workrise.domain.repository.ReportRepository
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

}
