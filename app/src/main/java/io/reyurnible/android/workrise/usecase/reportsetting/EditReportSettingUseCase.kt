package io.reyurnible.android.workrise.usecase.reportsetting

import io.reactivex.Completable
import io.reyurnible.android.workrise.domain.model.entity.FormSetting

/**
 * Editing ReportSetting UseCase
 */
interface EditReportSettingUseCase {
    fun edit(formSettings: List<FormSetting>): Completable
}
