package io.reyurnible.android.workrise.domain.repository.param

import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * 日報作成用のモデル
 */
data class ReportEditingParam(
        val date: YearMonthDay,
        val content: List<FormEditingParam>
)

