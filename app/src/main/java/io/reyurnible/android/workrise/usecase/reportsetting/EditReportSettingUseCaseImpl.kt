package io.reyurnible.android.workrise.usecase.reportsetting

import io.reactivex.Completable
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import io.reyurnible.android.workrise.domain.repository.ReportSettingRepository

class EditReportSettingUseCaseImpl(private val reportSettingRepository: ReportSettingRepository) : EditReportSettingUseCase {
    override fun edit(formSettings: List<FormSetting>): Completable =
            reportSettingRepository.editReportSetting(ReportSetting(formSettings))
}
