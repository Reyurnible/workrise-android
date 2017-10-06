package io.reyurnible.android.workrise.usecase.report

import io.reactivex.Observable
import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.Optional
import io.reyurnible.android.workrise.domain.repository.ReportRepository

class GetReportUseCaseImpl(private val reportRepository: ReportRepository) : GetReportUseCase {
    override fun exist(id: ReportId): Single<Boolean> =
            reportRepository.existReport(id)

    override fun get(id: ReportId): Single<Report> =
            reportRepository.fetchReport(id)

    override fun bind(id: ReportId): Observable<Optional<Report>> =
            reportRepository.observeReport(id)
}
