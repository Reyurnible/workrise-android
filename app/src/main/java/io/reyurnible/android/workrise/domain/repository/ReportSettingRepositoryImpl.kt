package io.reyurnible.android.workrise.domain.repository

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reyurnible.android.workrise.domain.factory.defaultInstance
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import io.reyurnible.android.workrise.infrastructure.pref.WorkrisePreferences

class ReportSettingRepositoryImpl(private val context: Context, private val workrisePreferences: WorkrisePreferences) : ReportSettingRepository {

    override fun fetchReportSetting(): Single<ReportSetting> =
            Single.create<ReportSetting> { source ->
                (workrisePreferences.reportSetting ?: ReportSetting.Factory.defaultInstance(context))
                        .let(source::onSuccess)
            }.subscribeOn(Schedulers.io())

    override fun editReportSetting(reportSetting: ReportSetting): Completable =
            Completable.create { source ->
                workrisePreferences.reportSetting = reportSetting
                source.onComplete()
            }.subscribeOn(Schedulers.io())

}
