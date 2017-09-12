package io.reyurnible.android.workrise.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Disposable Extension
 */
fun Disposable.addDisposableToBag(bag: CompositeDisposable) {
    bag.add(this)
}
