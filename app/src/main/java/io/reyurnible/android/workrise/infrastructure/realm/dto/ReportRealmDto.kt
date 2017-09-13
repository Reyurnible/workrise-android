package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

open class ReportRealmDto(
        @PrimaryKey
        var id: Int = 0,
        var date: String = "",
        var content: RealmList<FormRealmDto> = RealmList()
) : RealmObject()
