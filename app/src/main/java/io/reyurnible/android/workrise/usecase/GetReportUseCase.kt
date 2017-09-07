package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * Getting Report UseCase
 */
interface GetReportUseCase {
    /**
     * @param date
     */
    fun get(date: YearMonthDay): Single<Report>
}
