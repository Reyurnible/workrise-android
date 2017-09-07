package io.reyurnible.android.workrise.domain.model.entity

import io.reyurnible.android.workrise.domain.model.identifier.ReportId

/**
 * 日報のデータ
 */
data class Report(
        val id: ReportId,
        val content: List<Form>
)
