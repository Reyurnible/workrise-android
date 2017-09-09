package io.reyurnible.android.workrise.domain.repository

import io.reactivex.Single
import io.realm.MutableRealmInteger
import io.realm.RealmList
import io.realm.Sort
import io.realm.exceptions.RealmException
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
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
                                        .equalTo("id", id.value.toInteger())
                                        .findFirstAsync()
                                        ?.let(ReportConverter::convert)
                                        ?.let(source::onSuccess)
                                        // レポートがなかった場合は、ReportNotExistExceptionを返す
                                        ?: throw ReportRepository.ReportNotExistException("Report id ${id.value.toString()} is not exist.")
                            } catch (e: RealmException) {
                                source.onError(e)
                            } catch (e: ReportRepository.ReportNotExistException) {
                                source.onError(e)
                            } finally {
                                realm.close()
                            }
                        }
                    }

    override fun fetchReportList(minId: ReportId?, maxId: ReportId?, order: Int): Single<List<Report>> =
            realmFactory.createInstance()
                    .flatMap { realm ->
                        Single.create<List<Report>> { source ->
                            try {
                                realm.where(ReportRealmDto::class.java)
                                        .apply {
                                            minId?.let { greaterThan("id", it.value.toInteger()) }
                                            maxId?.let { lessThan("id", it.value.toInteger()) }
                                        }
                                        .findAllSortedAsync("id", Sort.DESCENDING)
                                        .toMutableList()
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
                                // Set Cancellable
                                source.setCancellable {
                                    realm.cancelTransaction()
                                }
                                // Execute Transaction
                                realm.executeTransaction { realm ->
                                    val reportDto = ReportRealmDto(
                                            id = param.date.toInteger(),
                                            date = param.date.toString(),
                                            content = RealmList()
                                    )
                                    reportDto.content.addAll(param.content.map { formParam ->
                                        when (formParam) {
                                            is ReportEditingParam.FormEditingParam.Text -> {
                                                FormRealmDto(
                                                        id = MutableRealmInteger.valueOf(realm.where(FormRealmDto::class.java).count()).apply {
                                                            increment(1)
                                                        },
                                                        type = FormRealmDto.Type.text,
                                                        title = formParam.title,
                                                        content = formParam.content,
                                                        checkItemList = RealmList(),
                                                        report = null
                                                )
                                            }
                                            is ReportEditingParam.FormEditingParam.CheckList -> {
                                                FormRealmDto(
                                                        id = MutableRealmInteger.valueOf(realm.where(FormRealmDto::class.java).count()).apply {
                                                            increment(1)
                                                        },
                                                        type = FormRealmDto.Type.checkList,
                                                        title = formParam.title,
                                                        content = null,
                                                        checkItemList = RealmList(),
                                                        report = null
                                                ).apply {
                                                    checkItemList.addAll(formParam.content.map { checkItemParam ->
                                                        val checkItemDto = CheckItemRealmDto(
                                                                id = MutableRealmInteger.valueOf(realm.where(CheckItemRealmDto::class.java).count()).apply {
                                                                    increment(1)
                                                                },
                                                                content = checkItemParam.content,
                                                                checked = checkItemParam.checked
                                                        )
                                                        realm.copyToRealm(checkItemDto)
                                                    })
                                                }
                                            }
                                        }.run {
                                            realm.copyToRealm(this)
                                        }
                                    })
                                    realm.copyToRealmOrUpdate(reportDto)
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
