package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * Getting Report List UseCase
 */
interface GetReportListUseCase {
    /**
     * @param previousDate
     * @param nextDate
     * @param order
     */
    fun get(previousDate: YearMonthDay, nextDate: YearMonthDay, order: Int): Single<List<Report>>
}
