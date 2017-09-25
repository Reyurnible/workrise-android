package io.reyurnible.android.workrise.presentation.form

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reyurnible.android.workrise.common.addDisposableToBag
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.ReportRepository
import io.reyurnible.android.workrise.domain.repository.param.FormEditingParam
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam
import io.reyurnible.android.workrise.usecase.report.CreateReportUseCase
import io.reyurnible.android.workrise.usecase.report.GetReportUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class FormPresenter
@Inject constructor(
        private val getReportUseCase: GetReportUseCase,
        private val createReportUseCase: CreateReportUseCase
) {
    private var date: YearMonthDay by Delegates.notNull()
    private lateinit var view: FormView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun initialize(view: FormView, date: YearMonthDay) {
        this.date = date
        this.view = view

        getReportUseCase.get(id = ReportId(date))
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { error ->
                    if (error is ReportRepository.ReportNotExistException) Single.never<Report>()
                    else Single.error<Report>(error)
                }
                .subscribe({ report ->
                    // Don't Action
                }, { error ->
                    view.showErrorDialog(error)
                }).addDisposableToBag(disposableBag)
    }

    fun release() {
        disposableBag.clear()
    }

    fun clickSave(formContent: List<FormEditingParam>) {
        view.showLoadingDialog()
        createReportUseCase.create(ReportEditingParam(date, formContent))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { _, _ -> view.hideLoadingDialog() }
                .subscribe({ report ->
                    view.finish()
                    // view.showReportDetails(report)
                }, { error ->
                    view.showErrorDialog(error)
                }).addDisposableToBag(disposableBag)
    }

    interface FormView {
        fun showReportDetails(report: Report)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showErrorDialog(error: Throwable)
        fun finish()
    }
}
