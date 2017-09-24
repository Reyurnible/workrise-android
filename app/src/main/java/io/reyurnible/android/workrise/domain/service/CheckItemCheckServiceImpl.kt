package io.reyurnible.android.workrise.domain.service

import io.reactivex.Completable
import io.reyurnible.android.workrise.common.equalTo
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId
import io.reyurnible.android.workrise.infrastructure.realm.RealmFactory
import io.reyurnible.android.workrise.infrastructure.realm.dto.CheckItemRealmDto

class CheckItemCheckServiceImpl(private val realmFactory: RealmFactory) : CheckItemCheckService {

    override fun checkCheckItem(id: CheckItemId): Completable =
            editCheckItemCheck(id, true)

    override fun uncheckCheckItem(id: CheckItemId): Completable =
            editCheckItemCheck(id, false)

    private fun editCheckItemCheck(id: CheckItemId, check: Boolean): Completable =
            realmFactory.createInstance()
                    .flatMapCompletable { realm ->
                        Completable.create { source ->
                            try {
                                realm.executeTransactionAsync({ bgRealm ->
                                    val checkItem: CheckItemRealmDto = bgRealm.where(CheckItemRealmDto::class.java)
                                            .equalTo(CheckItemRealmDto::id, id.value)
                                            .findFirst() ?: throw CheckItemCheckService.CheckItemNotExistException(id)
                                    if (checkItem.checked == check) {
                                        throw CheckItemCheckService.AlreadyStateException(id, check)
                                    }
                                    checkItem.checked = check
                                }, {
                                    source.onComplete()
                                }, { error ->
                                    source.onError(error)
                                })
                            } catch (e: CheckItemCheckService.CheckItemNotExistException) {
                                source.onError(e)
                            } catch (e: CheckItemCheckService.AlreadyStateException) {
                                source.onError(e)
                            } catch (e: Throwable) {
                                source.onError(e)
                            } finally {
                                realm.close()
                            }
                        }
                    }

}
