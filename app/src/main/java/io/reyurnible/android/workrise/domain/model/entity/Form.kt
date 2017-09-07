package io.reyurnible.android.workrise.domain.model.entity

import io.reyurnible.android.workrise.domain.model.identifier.FormId
import io.reyurnible.android.workrise.domain.model.identifier.ReportId

/**
 * 日報のフォーム
 */
sealed class Form(open val id: FormId, open val reportId: ReportId, open val title: String) {
    data class Text(
            override val id: FormId,
            override val reportId: ReportId,
            override val title: String,
            val content: String
    ) : Form(id, reportId, title)

    data class CheckList(
            override val id: FormId,
            override val reportId: ReportId,
            override val title: String,
            val content: List<CheckItem>
    ) : Form(id, reportId, title)
}
