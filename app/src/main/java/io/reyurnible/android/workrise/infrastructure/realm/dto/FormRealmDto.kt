package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.annotations.Index
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
class FormRealmDto(
        @PrimaryKey
        var id: Long,
        @Index
        var type: String,
        @Index
        var title: String,
        @Index
        var content: String?,
        var checkItemList: RealmList<CheckItemRealmDto>,
        @LinkingObjects("content")
        val report: RealmResults<ReportRealmDto>? = null
) : RealmModel {
    object Type {
        const val text = "text"
        const val checkList = "check_list"
    }
}
