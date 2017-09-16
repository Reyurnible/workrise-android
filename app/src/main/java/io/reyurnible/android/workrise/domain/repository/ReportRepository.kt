package io.reyurnible.android.workrise.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.Optional
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam

interface ReportRepository {
    fun fetchReport(id: ReportId): Single<Report>

    fun observeReport(id: ReportId): Observable<Optional<Report>>

    fun fetchReportList(minId: ReportId?, maxId: ReportId?, count: Int): Single<List<Report>>

    fun editReport(param: ReportEditingParam): Single<Report>

    class ReportNotExistException(message: String) : Exception(message)
}
