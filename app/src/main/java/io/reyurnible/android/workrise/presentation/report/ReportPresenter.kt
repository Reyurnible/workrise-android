package io.reyurnible.android.workrise.presentation.report

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reyurnible.android.workrise.extensions.addDisposableToBag
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.usecase.checkitem.CheckCheckItemUseCase
import io.reyurnible.android.workrise.usecase.report.GetReportUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class ReportPresenter
@Inject constructor(
        private val getReportUseCase: GetReportUseCase,
        private val checkCheckItemUseCase: CheckCheckItemUseCase
) {
    private var date: YearMonthDay by Delegates.notNull()
    private lateinit var view: ReportPresenter.ReportView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun initialize(view: ReportPresenter.ReportView, date: YearMonthDay) {
        this.date = date
        this.view = view
        getReportUseCase.bind(id = ReportId(date))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ report ->
                    view.setReport(report.value)
                }, { error ->
                    view.showErrorDialog(error)
                }, {

                }).addDisposableToBag(disposableBag)
    }

    fun release() {
        disposableBag.clear()
    }

    fun clickCreate() {
        view.showForm(date)
    }

    fun clickEdit() {
        view.showForm(date)
    }

    fun clickShare() {
        view.showShareMenu()
    }

    fun changeCheckChecklistFormItem(checkItemId: CheckItemId, checked: Boolean) {
        (if (checked) {
            checkCheckItemUseCase.check(checkItemId)
        } else {
            checkCheckItemUseCase.uncheck(checkItemId)
        }).subscribe({
            // Don't action
        }, { error ->
            view.showErrorDialog(error)
        }).addDisposableToBag(disposableBag)
    }

    interface ReportView {
        fun setReport(report: Report?)
        fun showForm(date: YearMonthDay)
        fun showShareMenu()
        fun showErrorDialog(error: Throwable)
    }

}
