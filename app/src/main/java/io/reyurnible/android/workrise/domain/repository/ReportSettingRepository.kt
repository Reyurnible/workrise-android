package io.reyurnible.android.workrise.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting

interface ReportSettingRepository {
    fun fetchReportSetting(): Single<ReportSetting>

    fun editReportSetting(reportSetting: ReportSetting): Completable
}
