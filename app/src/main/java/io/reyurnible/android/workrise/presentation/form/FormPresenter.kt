package io.reyurnible.android.workrise.presentation.form

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reyurnible.android.workrise.common.addDisposableToBag
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.param.FormEditingParam
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam
import io.reyurnible.android.workrise.usecase.CreateReportUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class FormPresenter
@Inject constructor(
        private val createReportUseCase: CreateReportUseCase
) {
    private var date: YearMonthDay by Delegates.notNull()
    private lateinit var view: FormView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun initialize(view: FormView, date: YearMonthDay) {
        this.date = date
        this.view = view
    }

    fun release() {
        disposableBag.clear()
    }

    fun clickSave(formContent: List<FormEditingParam>) {
        createReportUseCase.create(ReportEditingParam(date, formContent))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ report ->
                    view.finish()
                    // view.showReportDetails(report)
                }, { error ->
                    view.showErrorDialog(error)
                }).addDisposableToBag(disposableBag)
    }

    interface FormView {
        fun showReportDetails(report: Report)
        fun finish()
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showErrorDialog(error: Throwable)
    }
}
