package io.reyurnible.android.workrise.usecase.report

import io.reactivex.Observable
import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.Optional

/**
 * Getting Report UseCase
 */
interface GetReportUseCase {
    /**
     * @param id -> report id
     */
    fun exist(id: ReportId): Single<Boolean>
    /**
     * @param id -> report id
     */
    fun get(id: ReportId): Single<Report>

    /**
     * @param id -> report id
     */
    fun bind(id: ReportId): Observable<Optional<Report>>
}
