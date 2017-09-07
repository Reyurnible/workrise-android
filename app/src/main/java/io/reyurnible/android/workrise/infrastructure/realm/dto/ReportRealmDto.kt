package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.identifier.ReportId

@RealmClass
class ReportRealmDto(
        @PrimaryKey
        val date: String,
        val content: List<Form>
) : RealmObject()
