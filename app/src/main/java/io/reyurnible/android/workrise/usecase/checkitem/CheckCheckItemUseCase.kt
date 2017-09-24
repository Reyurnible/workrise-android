package io.reyurnible.android.workrise.usecase.checkitem

import io.reactivex.Completable
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId

/**
 * Check CheckItem UseCase
 */
interface CheckCheckItemUseCase {

    /**
     * チェックアイテムをチェック状態にする
     * @param id 対象のチェックアイテムのID
     */
    fun check(id: CheckItemId): Completable

    /**
     * チェックアイテムを未チェック状態にする
     * @param id 対象のチェックアイテムのID
     */
    fun uncheck(id: CheckItemId): Completable
}
