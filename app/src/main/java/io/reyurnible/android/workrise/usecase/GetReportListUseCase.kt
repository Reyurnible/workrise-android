package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * Getting Report List UseCase
 */
interface GetReportListUseCase {
    /**
     * @param prevId
     * @param nextId
     * @param order
     */
    fun get(prevId: ReportId?, nextId: ReportId?, order: Int): Single<List<Report>>
}
