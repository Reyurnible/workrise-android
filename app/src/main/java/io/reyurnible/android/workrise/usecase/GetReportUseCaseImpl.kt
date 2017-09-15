package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.ReportRepository

class GetReportUseCaseImpl(private val reportRepository: ReportRepository) : GetReportUseCase {

    override fun get(id: ReportId): Single<Report> =
            reportRepository.fetchReport(id)
}