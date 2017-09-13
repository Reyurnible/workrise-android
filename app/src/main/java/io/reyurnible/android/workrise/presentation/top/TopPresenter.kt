package io.reyurnible.android.workrise.presentation.top

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reyurnible.android.workrise.common.addDisposableToBag
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.usecase.GetDailyReportListUseCase
import javax.inject.Inject

class TopPresenter
@Inject constructor(
        private val getDailyReportListUseCase: GetDailyReportListUseCase
) {
    companion object {
        const val REQUEST_COUNT = 20
    }

    private lateinit var view: TopView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    private var currentPosition: Int? = null
    private val dailyReportList: MutableList<Pair<YearMonthDay, Report?>> = mutableListOf()

    fun initialize(view: TopView) {
        this.view = view
        getPrevDailyReport()
    }

    fun release() {
        disposableBag.clear()
    }

    fun selectedPage(position: Int) {
        currentPosition = position
        if (position == 0) {
            getPrevDailyReport()
        } else if (position == dailyReportList.size - 1) {
            getNextDailyReport()
        }
    }

    private fun getPrevDailyReport() {
        getDailyReportListUseCase
                .getPrev(prevDate = dailyReportList.firstOrNull()?.first, count = REQUEST_COUNT)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    if (data.isEmpty()) return@subscribe

                    dailyReportList.addAll(data)
                    dailyReportList.sortBy { it.first }
                    view.setDailyReportList(dailyReportList)
                    // 初回のリクエストかどうかのチェック
                    if (dailyReportList.size == data.size) {
                        // 最後のページ
                        view.setCurrentPosition(dailyReportList.lastIndex)
                        currentPosition = dailyReportList.lastIndex
                    } else {
                        // リクエスト前の最後のページ
                        view.setCurrentPosition(data.size)
                        currentPosition = data.size
                    }
                }, {
                    it.printStackTrace()
                }).addDisposableToBag(disposableBag)
    }

    private fun getNextDailyReport() {
        getDailyReportListUseCase
                .getNext(nextDate = dailyReportList.firstOrNull()?.first, count = REQUEST_COUNT)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    if (data.isEmpty()) return@subscribe

                    dailyReportList.addAll(data)
                    dailyReportList.sortBy { it.first }
                    view.setDailyReportList(dailyReportList)
                    // 初回のリクエストかどうかのチェック
                    if (dailyReportList.size == data.size) {
                        // 最後のページ
                        view.setCurrentPosition(0)
                        currentPosition = 0
                    } else {
                        // リクエスト前の最後のページ
                        view.setCurrentPosition(dailyReportList.lastIndex - data.size)
                        currentPosition = dailyReportList.lastIndex - data.size
                    }
                }, {

                }).addDisposableToBag(disposableBag)
    }

    interface TopView {
        fun setDailyReportList(dailyReportList: List<Pair<YearMonthDay, Report?>>)
        fun setCurrentPosition(position: Int)
    }

}
