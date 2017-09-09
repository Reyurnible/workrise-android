package io.reyurnible.android.workrise.domain.repository.param

import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * 日報作成用のモデル
 */
data class ReportEditingParam(
        val date: YearMonthDay,
        val content: List<FormEditingParam>
) {
    sealed class FormEditingParam(open val title: String) {
        data class Text(
                override val title: String,
                val content: String
        ) : FormEditingParam(title)

        data class CheckList(
                override val title: String,
                val content: List<CheckItemEditingParam>
        ) : FormEditingParam(title)
    }

    data class CheckItemEditingParam(
            val content: String,
            val checked: Boolean
    )
}

