package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.ReportRepository
import java.util.*

class GetDailyReportListUseCaseImpl(private val reportRepository: ReportRepository) : GetDailyReportListUseCase {

    override fun getPrev(prevDate: YearMonthDay?, count: Int): Single<List<Pair<YearMonthDay, Report?>>> {
        val maxDate = prevDate ?: YearMonthDay(Date())
        val minDate = Calendar.getInstance().apply {
            time = maxDate.toDate()
            add(Calendar.DATE, -count)
        }.let { YearMonthDay(it) }
        return reportRepository.fetchReportList(
                minId = ReportId(minDate),
                maxId = ReportId(maxDate),
                count = count
        ).map { reports ->
            // prevDate自体は含まないので、1から
            (1..count).map {
                // 日付リストの作成
                Calendar.getInstance().apply {
                    time = maxDate.toDate()
                    add(Calendar.DATE, -it)
                }.let { YearMonthDay(it) }
            }.map { date ->
                // レポートの結びつけ
                Pair(date, reports.find { it.id == ReportId(date) })
            }
        }
    }

    override fun getNext(nextDate: YearMonthDay?, count: Int): Single<List<Pair<YearMonthDay, Report?>>> {
        val minDate = nextDate ?: YearMonthDay(Date())
        val maxDate = Calendar.getInstance().apply {
            time = minDate.toDate()
            add(Calendar.DATE, count)
        }.let { YearMonthDay(it) }
        return reportRepository.fetchReportList(
                minId = ReportId(minDate),
                maxId = ReportId(maxDate),
                count = count
        ).map { reports ->
            // prevDate自体は含まないので、1から
            (1..count).map {
                // 日付リストの作成
                Calendar.getInstance().apply {
                    time = maxDate.toDate()
                    add(Calendar.DATE, it)
                }.let { YearMonthDay(it) }
            }.map { date ->
                // レポートの結びつけ
                Pair(date, reports.find { it.id == ReportId(date) })
            }
        }
    }

}
