package io.reyurnible.android.workrise.usecase.reportsetting

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import io.reyurnible.android.workrise.domain.repository.ReportSettingRepository

class GetReportSettingUseCaseImpl(private val reportSettingRepository: ReportSettingRepository) : GetReportSettingUseCase {
    override fun get(): Single<ReportSetting> =
            reportSettingRepository.fetchReportSetting()
}
