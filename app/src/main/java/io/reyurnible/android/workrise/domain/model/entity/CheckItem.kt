package io.reyurnible.android.workrise.domain.model.entity

import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId

/**
 * 日報のチェックリストフォームの1つのチェックアイテム
 */
data class CheckItem(
        val id: CheckItemId,
        val content: String,
        val checked: Boolean
)
