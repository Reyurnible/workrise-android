package io.reyurnible.android.workrise.infrastructure.realm.dto

import io.realm.MutableRealmInteger
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
class CheckItemRealmDto(
        @PrimaryKey
        val id: MutableRealmInteger,
        @Index
        val content: String,
        @Index
        val checked: Boolean
) : RealmObject()
