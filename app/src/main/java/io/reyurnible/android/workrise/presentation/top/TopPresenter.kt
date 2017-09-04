package io.reyurnible.android.workrise.presentation.top

import io.reactivex.disposables.CompositeDisposable

class TopPresenter {
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun initialize() {

    }

    fun release() {
        disposable.clear()
    }

    interface TopView {

    }

}
