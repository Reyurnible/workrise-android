package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.repository.ReportRepository
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam

class CreateReportUseCaseImpl(private val reportRepository: ReportRepository) : CreateReportUseCase {
    override fun create(param: ReportEditingParam): Single<Report> =
            reportRepository.editReport(param).observeOn(Schedulers.io())
}
