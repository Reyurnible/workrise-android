package io.reyurnible.android.workrise.usecase.report

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * Getting Date + Report? UseCase
 */
interface GetDailyReportListUseCase {
    /**
     * 指定した値よりも前の日付でcount分の値を作成して返す
     * @param prevDate
     * @param count 取得する日数
     */
    fun getPrev(prevDate: YearMonthDay? = null, count: Int): Single<List<Pair<YearMonthDay, Report?>>>

    /**
     * 指定した値よりも後の日付でcount分の日付の値を作成して返す
     * @param nextDate
     * @param count 取得する日数
     */
    fun getNext(nextDate: YearMonthDay? = null, count: Int): Single<List<Pair<YearMonthDay, Report?>>>
}
