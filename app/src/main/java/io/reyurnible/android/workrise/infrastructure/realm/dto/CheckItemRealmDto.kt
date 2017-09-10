package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
data class CheckItemRealmDto(
        @PrimaryKey
        val id: Long,
        @Index
        val content: String,
        @Index
        val checked: Boolean
) : RealmObject()
