package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * Getting Date + Report? UseCase
 */
interface GetDailyReportListUseCase {
    /**
     * @param prevDate
     * @param nextDate
     * @param order
     */
    fun get(prevDate: YearMonthDay?, nextDate: YearMonthDay?, order: Int): Single<List<Pair<YearMonthDay, Report?>>>
}
