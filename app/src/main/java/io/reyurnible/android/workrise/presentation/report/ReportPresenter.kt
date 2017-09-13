package io.reyurnible.android.workrise.presentation.report

import io.reactivex.disposables.CompositeDisposable

class ReportPresenter {

    private lateinit var view: ReportPresenter.ReportView
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun initialize(view: ReportPresenter.ReportView) {
        this.view = view
    }

    fun release() {
        disposableBag.clear()
    }

    interface ReportView {

    }

}
