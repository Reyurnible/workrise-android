package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
class CheckItemRealmDto(
        @PrimaryKey
        val id: Long,
        val content: String,
        val checked: Boolean
) : RealmObject()
