package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
class ReportRealmDto(
        @PrimaryKey
        var id: Int,
        var date: String,
        var content: RealmList<FormRealmDto>
) : RealmModel
