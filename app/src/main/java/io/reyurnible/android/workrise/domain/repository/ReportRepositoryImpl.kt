package io.reyurnible.android.workrise.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmList
import io.realm.Sort
import io.realm.exceptions.RealmException
import io.reyurnible.android.workrise.common.equalTo
import io.reyurnible.android.workrise.common.findAllSortedAsync
import io.reyurnible.android.workrise.common.greaterThan
import io.reyurnible.android.workrise.common.lessThan
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.Optional
import io.reyurnible.android.workrise.domain.repository.param.FormEditingParam
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam
import io.reyurnible.android.workrise.infrastructure.realm.RealmFactory
import io.reyurnible.android.workrise.infrastructure.realm.dto.CheckItemRealmDto
import io.reyurnible.android.workrise.infrastructure.realm.dto.FormRealmDto
import io.reyurnible.android.workrise.infrastructure.realm.dto.ReportRealmDto

class ReportRepositoryImpl(private val realmFactory: RealmFactory) : ReportRepository {

    override fun fetchReport(id: ReportId): Single<Report> =
            realmFactory.createInstance()
                    .flatMap { realm ->
                        Single.create<Report> { source ->
                            try {
                                realm.where(ReportRealmDto::class.java)
                                        .equalTo(ReportRealmDto::id, id.value.toInteger())
                                        .findFirst()
                                        ?.let(ReportConverter::convert)
                                        ?.let(source::onSuccess)
                                        // レポートがなかった場合は、ReportNotExistExceptionを返す
                                        ?: throw ReportRepository.ReportNotExistException(id)
                            } catch (e: ReportRepository.ReportNotExistException) {
                                source.onError(e)
                            } catch (e: Throwable) {
                                source.onError(e)
                            } finally {
                                realm.close()
                            }
                        }
                    }

    override fun observeReport(id: ReportId): Observable<Optional<Report>> =
            realmFactory.createInstance()
                    .flatMapObservable { realm ->
                        Observable.create<Optional<Report>> { source ->
                            try {
                                realm.where(ReportRealmDto::class.java)
                                        .equalTo(ReportRealmDto::id, id.value.toInteger())
                                        .findAllAsync()
                                        .addChangeListener { dto ->
                                            if (!source.isDisposed) {
                                                val report = dto.firstOrNull()?.let(ReportConverter::convert)
                                                source.onNext(Optional.ofNull(report))
                                            }
                                        }
                            } catch (e: Throwable) {
                                source.onError(e)
                            }
                            source.setCancellable {
                                realm.close()
                            }
                        }
                    }

    override fun fetchReportList(minId: ReportId?, maxId: ReportId?, count: Int): Single<List<Report>> =
            realmFactory.createInstance()
                    .flatMap { realm ->
                        Single.create<List<Report>> { source ->
                            try {
                                realm.where(ReportRealmDto::class.java)
                                        .apply {
                                            minId?.let { greaterThan(ReportRealmDto::id, it.value.toInteger()) }
                                            maxId?.let { lessThan(ReportRealmDto::id, it.value.toInteger()) }
                                        }
                                        .run {
                                            // 下のIDから
                                            if (minId != null) {
                                                findAllSortedAsync(ReportRealmDto::id, Sort.ASCENDING)
                                            } else {
                                                findAllSortedAsync(ReportRealmDto::id, Sort.DESCENDING)
                                            }
                                        }
                                        .toMutableList()
                                        .take(count)
                                        .sortedByDescending { it.id }
                                        .map(ReportConverter::convert)
                                        .let(source::onSuccess)
                            } catch (e: RealmException) {
                                source.onError(e)
                            } catch (e: ReportRepository.ReportNotExistException) {
                                source.onError(e)
                            } finally {
                                realm.close()
                            }
                        }
                    }

    override fun editReport(param: ReportEditingParam): Single<Report> =
            realmFactory.createInstance()
                    .flatMap { realm ->
                        Single.create<ReportRealmDto> { source ->
                            try {
                                // Execute Transaction
                                realm.executeTransactionAsync { bgRealm ->
                                    val reportDto = ReportRealmDto(
                                            id = param.date.toInteger(),
                                            date = param.date.toString(),
                                            content = RealmList()
                                    )
                                    reportDto.content.addAll(param.content.map { formParam ->
                                        when (formParam) {
                                            is FormEditingParam.Text -> {
                                                FormRealmDto(
                                                        id = bgRealm.where(FormRealmDto::class.java).count().apply {
                                                            inc()
                                                        },
                                                        type = FormRealmDto.Type.text,
                                                        title = formParam.title,
                                                        content = formParam.content,
                                                        checkItemList = RealmList(),
                                                        report = null
                                                )
                                            }
                                            is FormEditingParam.CheckList -> {
                                                FormRealmDto(
                                                        id = bgRealm.where(FormRealmDto::class.java).count().apply {
                                                            inc()
                                                        },
                                                        type = FormRealmDto.Type.checkList,
                                                        title = formParam.title,
                                                        content = null,
                                                        checkItemList = RealmList(),
                                                        report = null
                                                ).apply {
                                                    checkItemList.addAll(formParam.content.map { checkItemParam ->
                                                        val checkItemDto = CheckItemRealmDto(
                                                                id = bgRealm.where(CheckItemRealmDto::class.java).count().apply {
                                                                    inc()
                                                                },
                                                                content = checkItemParam.content,
                                                                checked = checkItemParam.checked
                                                        )
                                                        bgRealm.copyToRealm(checkItemDto)
                                                    })
                                                }
                                            }
                                        }.run {
                                            bgRealm.copyToRealm(this)
                                        }
                                    })
                                    bgRealm.copyToRealmOrUpdate(reportDto)
                                    source.onSuccess(reportDto)
                                }
                            } catch (e: RealmException) {
                                source.onError(e)
                            } finally {
                                realm.close()
                            }
                        }
                    }.map(ReportConverter::convert)
}
