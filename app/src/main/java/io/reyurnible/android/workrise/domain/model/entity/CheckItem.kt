package io.reyurnible.android.workrise.domain.model.entity

import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId
import io.reyurnible.android.workrise.domain.model.identifier.FormId

/**
 * 日報のチェックリストフォームの1つのチェックアイテム
 */
data class CheckItem(
        val id: CheckItemId,
        val formId: FormId,
        val content: String,
        val checked: Boolean
) {
    companion object Factory
}
