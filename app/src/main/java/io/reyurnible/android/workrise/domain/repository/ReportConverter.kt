package io.reyurnible.android.workrise.domain.repository

import io.reyurnible.android.workrise.domain.model.entity.CheckItem
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId
import io.reyurnible.android.workrise.domain.model.identifier.FormId
import io.reyurnible.android.workrise.domain.model.identifier.ReportId
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.infrastructure.realm.dto.CheckItemRealmDto
import io.reyurnible.android.workrise.infrastructure.realm.dto.FormRealmDto
import io.reyurnible.android.workrise.infrastructure.realm.dto.ReportRealmDto

object ReportConverter {

    fun convert(dto: ReportRealmDto): Report = kotlin.run {
        val reportId = ReportId(YearMonthDay.createFromString(dto.date))
        Report(id = reportId, content = dto.content.map { convert(reportId, it) })
    }

    fun convert(reportId: ReportId, dto: FormRealmDto): Form = kotlin.run {
        val formId = FormId(checkNotNull(dto.id.get()))
        when (dto.type) {
            FormRealmDto.Type.text -> {
                Form.Text(
                        id = formId,
                        reportId = reportId,
                        title = dto.title,
                        content = dto.content
                )
            }
            FormRealmDto.Type.checkList -> {
                Form.CheckList(
                        id = formId,
                        reportId = reportId,
                        title = dto.title,
                        content = dto.checkItemList.map { convert(formId, it) }
                )
            }
            else -> throw IllegalArgumentException("Invalid form type.")
        }
    }

    fun convert(formId: FormId, dto: CheckItemRealmDto): CheckItem = kotlin.run {
        CheckItem(
                id = CheckItemId(checkNotNull(dto.id.get())),
                formId = formId,
                content = dto.content,
                checked = dto.checked
        )
    }
}
