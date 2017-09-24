package io.reyurnible.android.workrise.usecase.checkitem

import io.reactivex.Completable
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId
import io.reyurnible.android.workrise.domain.service.CheckItemCheckService

class CheckCheckItemUseCaseImpl(private val checkItemCheckService: CheckItemCheckService) : CheckCheckItemUseCase {

    override fun check(id: CheckItemId): Completable =
            checkItemCheckService.checkCheckItem(id).onErrorComplete {
                it is CheckItemCheckService.AlreadyStateException
            }

    override fun uncheck(id: CheckItemId): Completable =
            checkItemCheckService.uncheckCheckItem(id).onErrorComplete {
                it is CheckItemCheckService.AlreadyStateException
            }
}
