package io.reyurnible.android.workrise.presentation.reportsetting

import io.reactivex.disposables.CompositeDisposable
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import io.reyurnible.android.workrise.usecase.checkitem.CheckCheckItemUseCase
import io.reyurnible.android.workrise.usecase.reportsetting.GetReportSettingUseCase
import javax.inject.Inject

class ReportSettingPresenter
@Inject constructor(
        private val getReportSettingUseCase: GetReportSettingUseCase
) {
    private lateinit var view: ReportSettingPresenter.ReportSettingView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun initialize(view: ReportSettingPresenter.ReportSettingView) {
        this.view = view
    }

    fun release() {
        disposableBag.clear()
    }

    fun clickFormSettingItem(formSetting: FormSetting) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun clickAddFormSetting() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun clickEditFormSetting(formSetting: FormSetting) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun clickDeleteFormSetting(formSetting: FormSetting) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface ReportSettingView {
        fun setReportSetting(reportSetting: ReportSetting?)
        fun showErrorDialog(error: Throwable)
    }

}
