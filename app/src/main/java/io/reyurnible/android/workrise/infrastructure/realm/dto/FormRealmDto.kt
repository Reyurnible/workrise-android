package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
class FormRealmDto(
        @PrimaryKey
        val id: Long,
        val type: String,
        val content: String
) : RealmObject()
