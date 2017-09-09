package io.reyurnible.android.workrise.domain.repository

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam

interface ReportRepository {
    fun fetchReport(id: ReportId): Single<Report>

    fun fetchReportList(minId: ReportId?, maxId: ReportId?, order: Int): Single<List<Report>>

    fun editReport(param: ReportEditingParam): Single<Report>

    class ReportNotExistException(message: String) : Exception(message)
}
