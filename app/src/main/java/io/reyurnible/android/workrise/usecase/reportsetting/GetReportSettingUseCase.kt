package io.reyurnible.android.workrise.usecase.reportsetting

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting

/**
 * Getting ReportSetting UseCase
 */
interface GetReportSettingUseCase {

    fun get(): Single<ReportSetting>
}
