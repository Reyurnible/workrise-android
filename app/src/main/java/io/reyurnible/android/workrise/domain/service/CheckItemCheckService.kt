package io.reyurnible.android.workrise.domain.service

import io.reactivex.Completable
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId

interface CheckItemCheckService {
    fun checkCheckItem(id: CheckItemId): Completable
    fun uncheckCheckItem(id: CheckItemId): Completable

    class CheckItemNotExistException(id: CheckItemId) : Exception("CheckItem id ${id.value} is not exist.")
    class AlreadyStateException(id: CheckItemId, checked: Boolean) : Exception("CheckItem id ${id.value} is already ${if (checked) "checked" else "unchecked"}.")
}
