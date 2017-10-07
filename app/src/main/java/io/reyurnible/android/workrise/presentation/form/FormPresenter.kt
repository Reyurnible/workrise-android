package io.reyurnible.android.workrise.presentation.form

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.ReportRepository
import io.reyurnible.android.workrise.domain.repository.param.FormEditingParam
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam
import io.reyurnible.android.workrise.extensions.addDisposableToBag
import io.reyurnible.android.workrise.usecase.report.CreateReportUseCase
import io.reyurnible.android.workrise.usecase.report.GetReportUseCase
import io.reyurnible.android.workrise.usecase.reportsetting.GetReportSettingUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class FormPresenter
@Inject constructor(
        private val getReportSettingUseCase: GetReportSettingUseCase,
        private val getReportUseCase: GetReportUseCase,
        private val createReportUseCase: CreateReportUseCase
) {
    private var date: YearMonthDay by Delegates.notNull()
    private lateinit var view: FormView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun initialize(view: FormView, date: YearMonthDay) {
        this.date = date
        this.view = view
        getReportUseCase.exist(id = ReportId(date))
                .subscribe({ exist ->
                    if (exist) loadReport()
                    else loadReportSetting()
                }).addDisposableToBag(disposableBag)
    }

    fun release() {
        disposableBag.clear()
    }

    fun clickSave(formContent: List<FormEditingParam>) {
        view.lockSaveButton()
        createReportUseCase.create(ReportEditingParam(date, formContent))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { _, _ -> view.unlockSaveButton() }
                .subscribe({
                    view.finish()
                }, { error ->
                    view.showErrorDialog(error)
                }).addDisposableToBag(disposableBag)
    }

    private fun loadReportSetting() {
        getReportSettingUseCase.get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setReportSetting, view::showErrorDialog)
                .addDisposableToBag(disposableBag)
    }

    private fun loadReport() {
        getReportUseCase.get(id = ReportId(date))
                .onErrorResumeNext { error ->
                    if (error is ReportRepository.ReportNotExistException) Single.never<Report>()
                    else Single.error<Report>(error)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setReport, view::showErrorDialog)
                .addDisposableToBag(disposableBag)
    }

    interface FormView {
        fun setReportSetting(setting: ReportSetting)
        fun setReport(report: Report)
        fun lockSaveButton()
        fun unlockSaveButton()
        fun showErrorDialog(error: Throwable)
        fun finish()
    }
}
