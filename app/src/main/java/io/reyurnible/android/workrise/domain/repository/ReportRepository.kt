package io.reyurnible.android.workrise.domain.repository

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId

interface ReportRepository {
    fun fetchReport(id: ReportId): Single<Report>

    fun fetchReportList(prevId: ReportId?, nextId: ReportId?, order: Int): Single<List<Report>>
}
